package com.soltow.pos.listener;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soltow.pos.listener.DataQueueListener;

public class DataQueueListener_UT {
	private DataQueueListener dispatcher = new DataQueueListener();
	
	@Test
	public void test() {
		dispatcher.run();
		assertTrue(true);
	}

	@Test
	public void testIsStopMessage() {
		assertTrue("This should have been true for STOP", dispatcher.isStopRequest("STOP"));
		assertFalse("This should have been true for STOP", dispatcher.isStopRequest("asdfasdf!@#$^@^%"));
		assertFalse("This should have been true for STOP", dispatcher.isStopRequest(null));
	}
}
