package com.soltow.pos.listener;

import org.apache.log4j.Logger;

public class DataQueueListener implements MessageListener {
	private Logger logger = Logger.getLogger(this.getClass());

	public void init() {

	}
	
	public String readRequest() {
		return "";
	}
		
}
