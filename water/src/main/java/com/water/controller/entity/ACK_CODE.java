package com.water.controller.entity;

public enum ACK_CODE {
	
	NONE(-100),
	OK(0),
	NOT_EXIST(-1),
	LIMIT_ERR(-2),
	ILLEGAL(-3),
	EXIST(-4),
	NOT_LOCKING(-5),
	NOT_STANDBY(-6),
	TOKEN_ERR(-7),
	ERR(-8);
	
	private int value;
	
	private ACK_CODE(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	static public ACK_CODE int2Enum(int value) {
		ACK_CODE[] all = ACK_CODE.class.getEnumConstants();
		for (ACK_CODE item : all) {
			if (item.getValue() == value) {
				return item;
			}
		}
		
		return NONE;
	}
	
}
