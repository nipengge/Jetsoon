package com.jetsoon.bean;

import java.io.Serializable;

/**
 * 无人机驾驶员信息表
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-9 下午04:19:57
 */
public class Drone_Driver_Info implements Serializable {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 64585;

	private int droneDriverInfoId;
	
	private String driverName;
	
	private String idCard;
	
	private String nativePlace;
	
	private String driverType;
	
	private String droneIdCard;
	
	private String trainingSituation;
	
	private String politicalAppearance;
	
	private String issuingUnit;
	
	private int CompanyId;
	
	private String dactyloPath;
	
	private String facePath;
	
	private PagerBean pagerBean;

	public int getDroneDriverInfoId() {
		return droneDriverInfoId;
	}

	public void setDroneDriverInfoId(int droneDriverInfoId) {
		this.droneDriverInfoId = droneDriverInfoId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDroneIdCard() {
		return droneIdCard;
	}

	public void setDroneIdCard(String droneIdCard) {
		this.droneIdCard = droneIdCard;
	}

	public String getIssuingUnit() {
		return issuingUnit;
	}

	public void setIssuingUnit(String issuingUnit) {
		this.issuingUnit = issuingUnit;
	}
	
	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}
	
	public PagerBean getPagerBean() {
		return pagerBean;
	}
	
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getTrainingSituation() {
		return trainingSituation;
	}

	public void setTrainingSituation(String trainingSituation) {
		this.trainingSituation = trainingSituation;
	}

	public String getPoliticalAppearance() {
		return politicalAppearance;
	}

	public void setPoliticalAppearance(String politicalAppearance) {
		this.politicalAppearance = politicalAppearance;
	}

	public String getDactyloPath() {
		return dactyloPath;
	}

	public void setDactyloPath(String dactyloPath) {
		this.dactyloPath = dactyloPath;
	}

	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	public void setPagerBean(PagerBean pagerBean) {
		this.pagerBean = pagerBean;
	}

	@Override
	public String toString() {
		return "Drone_Driver_Info [CompanyId=" + CompanyId + ", driverName="
				+ driverName + ", droneDriverInfoId=" + droneDriverInfoId
				+ ", droneIdCard=" + droneIdCard + ", idCard=" + idCard
				+ ", issuingUnit=" + issuingUnit + "]";
	}
	

}
