package com.water.controller.entity;

public class ComAckEntity {

	private int err;
	private String msg;
	
	public ComAckEntity(int err, String msg) {
		this.err = err;
		this.msg = msg;
	}
	public int getErr() {
		return err;
	}
	public void setErr(int err) {
		this.err = err;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
