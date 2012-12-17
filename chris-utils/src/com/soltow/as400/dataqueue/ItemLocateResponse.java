package com.soltow.as400.dataqueue;

import org.apache.commons.lang3.StringUtils;


public class ItemLocateResponse {
	private Integer storeNumber;
	private Integer itemNumber;
	
	public Integer getStoreNumber() {
		return storeNumber;
	}
	public String getStoreNumberAsString() {
		return StringUtils.leftPad(storeNumber.toString(), 7);
	}
	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}
	public Integer getItemNumber() {
		return itemNumber;
	}
	public String getItemNumberAsString() {
		return StringUtils.leftPad(itemNumber.toString(), 7);
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	
}
