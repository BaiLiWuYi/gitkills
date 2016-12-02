package com.water.table;

public class TbAdmin {

	private String count;
	private String passwd;
	private int level;
	
	public TbAdmin(String count, String passwd, int level) {
		this.count = count;
		this.passwd = passwd;
		this.level = level;
	}
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
