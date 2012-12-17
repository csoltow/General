package com.soltow.pos.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.soltow.pos.handler.MessageHandler;
import com.soltow.pos.listener.MessageListener;

public class MessagingController implements Runnable {
	private static final Integer MAX_THREAD_POOL = new Integer(20);
	
	private Logger logger = Logger.getLogger(this.getClass());
	private Integer threadPoolSize = new Integer(10);
	private Integer threadCounter=0;
	private ThreadPoolExecutor executor;

	private MessageHandler handler;
	private MessageListener listener;

	public void init() {
		executor = new ThreadPoolExecutor(
				threadPoolSize, 
				MAX_THREAD_POOL, 
				1, 
				TimeUnit.MINUTES, 
				new ArrayBlockingQueue<Runnable>(MAX_THREAD_POOL, true), 
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		executor.prestartAllCoreThreads();
	}
	
	public void run() {
		while (!executor.isShutdown()) {
			String message = readMessage();
			handleRequest(executor, message);
			logger.debug("Active Thread count ["+executor.getActiveCount() );
		}

		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	protected String readMessage() {
		return listener.readRequest();
	}
	
	protected void handleRequest(ThreadPoolExecutor service, String message) {
		if(isStopRequest(message)) {
			service.shutdown();
		} else {
			threadCounter++;
			if(threadCounter > 100) {
				service.shutdown();
			} else {
				logger.trace("Spawning thread ["+threadCounter+"] completed task count["+service.getCompletedTaskCount()+"]");
				handler.setMessage(message);
				service.execute(handler);
			}
		}
	}

	protected boolean isStopRequest(String request) {
		return request != null && request.equals("STOP");
	}
	
	public void setThreadPoolSize(Integer size) {
		if(size > MAX_THREAD_POOL) {
			threadPoolSize = MAX_THREAD_POOL;
		} else {
			threadPoolSize = size;
		}
		
	}
	
	public Integer getThreadPoolSize() {
		return threadPoolSize;
	}

	public MessageHandler getRequestHandler() {
		return handler;
	}

	public void setRequestHandler(MessageHandler reqHandler) {
		this.handler = reqHandler;
	}

	public MessageListener getListener() {
		return listener;
	}

	public void setListener(MessageListener listener) {
		this.listener = listener;
	}
	
}
