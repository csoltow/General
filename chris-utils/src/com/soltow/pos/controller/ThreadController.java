package com.soltow.pos.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.soltow.pos.listener.DataQueueListener;

public class ThreadController {
	Logger logger = Logger.getLogger(this.getClass());
	private ExecutorService service = Executors.newCachedThreadPool();
	
	public void execute() {
		//for (iterable_type iterable_element : iterable) {
			service.execute(new MessagingController());
		//}
		service.shutdown();
		while(!service.isShutdown()) {
			logger.debug("waiting....");
		}
	}
	
	public ExecutorService getExecutorService() {
		return service;
	}
	
	public void setExecutorService(ExecutorService execService) {
		service = execService;
	}
	
	public static void main(String[] args) {
		ThreadController dispatcher = new ThreadController();
		dispatcher.execute();
	}
}
