package com.jetsoon.bean;

import java.io.Serializable;

/**
 * 企业信息表
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-6 上午11:04:10
 */
public class Company_Information implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 65546;

	private int eiId;
	
	private String companyName;
	
	private String companyAddress;
	
	private String companytel;
	
	private String subordinateIndustry;
	
	public String getCompanytel() {
		return companytel;
	}

	public void setCompanytel(String companytel) {
		this.companytel = companytel;
	}

	public int getEiId() {
		return eiId;
	}

	public void setEiId(int eiId) {
		this.eiId = eiId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getSubordinateIndustry() {
		return subordinateIndustry;
	}

	public void setSubordinateIndustry(String subordinateIndustry) {
		this.subordinateIndustry = subordinateIndustry;
	}

	@Override
	public String toString() {
		return "Company_Information [companyAddress=" + companyAddress
				+ ", companyName=" + companyName + ", companytel=" + companytel
				+ ", eiId=" + eiId + ", subordinateIndustry="
				+ subordinateIndustry + "]";
	}
	
	

}
