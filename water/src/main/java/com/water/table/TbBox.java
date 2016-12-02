package com.water.table;

import com.water.cabinet.define.BOX_STATUS;

public class TbBox {

	private int cabNo;
	private int boxNo;
	private int status;
	private String cardNo;
	private String wristNo;
	private String bigZone;
	private String smallZone;
	
	/**
	 * only called when add box.
	 * @param cabNo
	 * @param boxNo
	 * @param wristNo
	 * @param bigZone
	 * @param smallZone
	 */
	public TbBox(int cabNo, int boxNo, String wristNo, String bigZone, String smallZone) {
		this.cabNo = cabNo;
		this.boxNo = boxNo;
		this.status = BOX_STATUS.FREE.getValue();
		this.cardNo = null;
		this.wristNo = wristNo;
		this.bigZone = bigZone;
		this.smallZone = smallZone;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBigZone() {
		return bigZone;
	}
	public void setBigZone(String bigZone) {
		this.bigZone = bigZone;
	}
	public String getSmallZone() {
		return smallZone;
	}
	public void setSmallZone(String smallZone) {
		this.smallZone = smallZone;
	}
	public int getCabNo() {
		return cabNo;
	}
	public void setCabNo(int cabNo) {
		this.cabNo = cabNo;
	}
	public int getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(int boxNo) {
		this.boxNo = boxNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getWristNo() {
		return wristNo;
	}
	public void setWristNo(String wristNo) {
		this.wristNo = wristNo;
	}
	
}
