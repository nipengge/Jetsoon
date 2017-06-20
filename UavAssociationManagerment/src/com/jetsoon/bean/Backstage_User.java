package com.jetsoon.bean;

/**
 * 后台用户账号表
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-6 上午11:10:52
 */
public class Backstage_User {
	
	private int buId;
	
	private String userName;
	
	private String userPassword;
	
	private String bindAddressIp;
	
	public int getBuId() {
		return buId;
	}

	public void setBuId(int buId) {
		this.buId = buId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getBindAddressIp() {
		return bindAddressIp;
	}

	public void setBindAddressIp(String bindAddressIp) {
		this.bindAddressIp = bindAddressIp;
	}

}
