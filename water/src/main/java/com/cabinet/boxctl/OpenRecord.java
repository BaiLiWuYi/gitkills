package com.cabinet.boxctl;

public class OpenRecord {

	public static enum TYPE {
		NONE,
		USER,
		ADMIN,
	}
	
	private String ip;
	private String cardNo;
	private int boxNo;
	private TYPE type;	
	private String time;
	
	public OpenRecord(String ip, String cardNo, int boxNo, TYPE type, String time) {
		this.ip = ip;
		this.cardNo = cardNo;
		this.boxNo = boxNo;
		this.type = type;
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(int boxNo) {
		this.boxNo = boxNo;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}	
	
}
