package com.soltow.pos.handler;

import org.apache.log4j.Logger;

public class ItemLocateMessageHandler implements MessageHandler {
	private Logger logger = Logger.getLogger(this.getClass());
	private String message;
	
	public void run() {
		try {
			Thread.sleep(100);
			logger.debug("thread is awake and processing "+message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void setMessage(String msg) {
		message = msg;
	}

}
