package com.water.cabinet.define;

public enum BOX_STATUS {

	NONE(-1),
	USE(0),
	FREE(1),
	FAULT(2);
	
	private int value;
	
	private BOX_STATUS(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	static public BOX_STATUS int2Enum(int value) {
		BOX_STATUS[] all = BOX_STATUS.class.getEnumConstants();
		for (BOX_STATUS item : all) {
			if (item.getValue() == value) {
				return item;
			}
		}
		
		return NONE;
	}
	
}
