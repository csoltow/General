package com.soltow.as400.dataqueue;


public interface DataQueueMarshaller {
	
	public void init();
	
	public byte[] marshal(Object response);

}
