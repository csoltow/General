package com.soltow.as400.dataqueue;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.CharacterFieldDescription;
import com.ibm.as400.access.DataQueueEntry;
import com.ibm.as400.access.Record;
import com.ibm.as400.access.RecordFormat;

public class ItemLocateUnmarshaller {
	public static final String ITEM_NUMBER = "ITEM_NUMBER";
	public static final String STORE_NUMBER = "STORE_NUMBER";
	protected RecordFormat recordFormat = new RecordFormat();
	protected AS400 system;

	public ItemLocateUnmarshaller(AS400 as400) {
		system = as400;
	}
	
	public void init() {
		CharacterFieldDescription storeNumberDesc = new CharacterFieldDescription(new AS400Text(5, system), STORE_NUMBER);
		CharacterFieldDescription itemNumberDesc = new CharacterFieldDescription(new AS400Text(7, system), ITEM_NUMBER);
		recordFormat.addFieldDescription(storeNumberDesc);
		recordFormat.addFieldDescription(itemNumberDesc);
	}
	
	public ItemLocateRequest unmarshal(DataQueueEntry entry) {
        try {
			byte[] data = entry.getData();
            Record message = recordFormat.getNewRecord(data);
			ItemLocateRequest request = new ItemLocateRequest();
			request.setStoreNumber((Integer)message.getField(STORE_NUMBER));
			request.setItemNumber((Integer)message.getField(ITEM_NUMBER));
			return request;
		} catch (Exception e) {
			throw new DataQueueRuntimeException("Unable to unmarshal request.", e.getCause());
		}
	}

}
