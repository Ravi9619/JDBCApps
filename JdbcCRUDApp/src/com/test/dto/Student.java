package com.test.dto;

import java.io.Serializable;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int sid;
	private String sname;
	private int sage;
	private String saddress;
	private String gender;
	
	public int getSid() {
		return sid;
	}
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getSname() {
		return sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public int getSage() {
		return sage;
	}
	
	public void setSage(int sage) {
		this.sage = sage;
	}
	
	public String getSaddress() {
		return saddress;
	}
	
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
}
