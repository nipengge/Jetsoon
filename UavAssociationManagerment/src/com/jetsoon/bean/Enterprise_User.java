package com.jetsoon.bean;

import java.io.Serializable;

/**
 * 企业账户表
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-6 上午11:01:58
 */
public class Enterprise_User implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 55154;

	private int euId;
	
	private String accountName;
	
	private String euPassword;
	
	private int statu;
	
	private int role;
	
	private String registerDateTime;
	
	private int enterpriseInformationId;
	
	private PagerBean pagerBean;
	
	public int getEnterpriseInformationId() {
		return enterpriseInformationId;
	}

	public void setEnterpriseInformationId(int enterpriseInformationId) {
		this.enterpriseInformationId = enterpriseInformationId;
	}

	private Company_Information companyInformation;
	
	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public String getRegisterDateTime() {
		return registerDateTime;
	}

	public void setRegisterDateTime(String registerDateTime) {
		this.registerDateTime = registerDateTime;
	}


	public int getEuId() {
		return euId;
	}

	public void setEuId(int euId) {
		this.euId = euId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEuPassword() {
		return euPassword;
	}

	public void setEuPassword(String euPassword) {
		this.euPassword = euPassword;
	}

	public Company_Information getCompanyInformation() {
		return companyInformation;
	}

	public void setCompanyInformation(Company_Information companyInformation) {
		this.companyInformation = companyInformation;
	}
	

	public PagerBean getPagerBean() {
		return pagerBean;
	}

	public void setPagerBean(PagerBean pagerBean) {
		this.pagerBean = pagerBean;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Enterprise_User [accountName=" + accountName
				+ ", companyInformation=" + companyInformation + ", euId="
				+ euId + ", euPassword=" + euPassword + ", registerDateTime="
				+ registerDateTime + ", statu=" + statu + "]";
	}

}
