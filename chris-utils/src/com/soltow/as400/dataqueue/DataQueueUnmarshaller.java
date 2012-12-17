package com.soltow.as400.dataqueue;

import com.ibm.as400.access.DataQueueEntry;

public interface DataQueueUnmarshaller {
	
	public void init();
	
	public Object unmarshal(DataQueueEntry entry);
	
}
