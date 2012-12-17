package com.soltow.as400.dataqueue;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.CharacterFieldDescription;
import com.ibm.as400.access.Record;
import com.ibm.as400.access.RecordFormat;

public class ItemLocateMarshaller {
	public static final String ITEM_NUMBER = "ITEM_NUMBER";
	public static final String STORE_NUMBER = "STORE_NUMBER";
	protected RecordFormat recordFormat = new RecordFormat();
	protected AS400 system;

	public ItemLocateMarshaller(AS400 as400) {
		system = as400;
	}
	
	public void init() {
		CharacterFieldDescription storeNumberDesc = new CharacterFieldDescription(new AS400Text(5, system), STORE_NUMBER);
		CharacterFieldDescription itemNumberDesc = new CharacterFieldDescription(new AS400Text(7, system), ITEM_NUMBER);
		recordFormat.addFieldDescription(storeNumberDesc);
		recordFormat.addFieldDescription(itemNumberDesc);
	}
	
	public byte[] marshal(Object response) {
		try {
			ItemLocateResponse itemLocateResponse = (ItemLocateResponse)response;
			Record message = new Record(recordFormat);
			message.setField(STORE_NUMBER, itemLocateResponse.getStoreNumberAsString());
			message.setField(ITEM_NUMBER, itemLocateResponse.getItemNumberAsString());
			byte[] byteData = message.getContents();
			return byteData;
		} catch (Exception e) {
			throw new DataQueueRuntimeException("Unable to marshal response.", e.getCause());
		}
	}

}
