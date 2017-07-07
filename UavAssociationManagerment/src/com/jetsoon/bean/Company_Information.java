package com.jetsoon.bean;

import java.io.Serializable;

/**
 * ��ҵ��Ϣ��
 * @author nipengge
 * ����:������
 * ʱ��:2017-2-6 ����11:04:10
 */
public class Company_Information implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)
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
