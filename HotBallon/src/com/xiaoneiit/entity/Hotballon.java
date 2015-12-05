package com.xiaoneiit.entity;

/**
 * Base entity class
 * @author 狗蛋哥
 *
 */
public class Hotballon {
	/**
	 * user name
	 */
	private int name;
	/**
	 * user avator img url
	 */
	private int avator;
	/**
	 * drawable id
	 */
	private int popo;
	/**
	 * drawable id
	 */
	private int country;
	/**
	 * user message
	 */
	private int message;
	
	
	
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getAvator() {
		return avator;
	}
	public void setAvator(int avator) {
		this.avator = avator;
	}
	public int getPopo() {
		return popo;
	}
	public void setPopo(int popo) {
		this.popo = popo;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	
}
