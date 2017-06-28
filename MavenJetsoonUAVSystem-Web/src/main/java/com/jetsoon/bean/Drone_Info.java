package com.jetsoon.bean;

/**
 * 无人机信息表
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-6 上午11:06:58
 */
public class Drone_Info {
	
	private int diId;
	
	private String droneId;
	
	private String droneBrand;
	
	private String droneModel;
	
	private float droneLoad;
	
	private String dateManufacture;
	
	private String productionLot;
	
	private String useCompanyName;
	
	private String industry;
	
	private String purpose;
	
	private float weight;
	
	private float lifeTime ;
	
	private String operationDate;
	
	private int dronedriverinfoId;
	
	private int droneCompanyId;
	
	private int onLine;
	
	/**
	 * 查询条件
	 */
	private String unfixedValue; //条件查询Value
	
	private String unfixedKey;  //调价查询Key;
	
	private String unfixedValue1; //条件查询Value
	
	private String unfixedKey1;  //调价查询Key;
	
	private String unfixedValue2; //条件查询Value
	
	private String unfixedKey2;  //调价查询Key;
	
	private String unfixedValue3; //条件查询Value
	
	private String unfixedKey3;  //调价查询Key;
	
	
	
	private Company_Information companyInformation;
	
	private Drone_Driver_Info droneDriverInfo;
	

	public int getDiId() {
		return diId;
	}

	public void setDiId(int diId) {
		this.diId = diId;
	}

	public String getDroneId() {
		return droneId;
	}

	public void setDroneId(String droneId) {
		this.droneId = droneId;
	}

	public String getDroneBrand() {
		return droneBrand;
	}

	public void setDroneBrand(String droneBrand) {
		this.droneBrand = droneBrand;
	}

	public String getDroneModel() {
		return droneModel;
	}

	public void setDroneModel(String droneModel) {
		this.droneModel = droneModel;
	}

	public float getDroneLoad() {
		return droneLoad;
	}

	public void setDroneLoad(float droneLoad) {
		this.droneLoad = droneLoad;
	}

	
	public String getDateManufacture() {
		return dateManufacture;
	}

	public void setDateManufacture(String dateManufacture) {
		this.dateManufacture = dateManufacture;
	}
	

	public String getProductionLot() {
		return productionLot;
	}

	public void setProductionLot(String productionLot) {
		this.productionLot = productionLot;
	}

	public String getUseCompanyName() {
		return useCompanyName;
	}

	public void setUseCompanyName(String useCompanyName) {
		this.useCompanyName = useCompanyName;
	}

		
	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	

	public int getDronedriverinfoId() {
		return dronedriverinfoId;
	}

	public void setDronedriverinfoId(int dronedriverinfoId) {
		this.dronedriverinfoId = dronedriverinfoId;
	}

	public int getDroneCompanyId() {
		return droneCompanyId;
	}

	public void setDroneCompanyId(int droneCompanyId) {
		this.droneCompanyId = droneCompanyId;
	}
	

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(float lifeTime) {
		this.lifeTime = lifeTime;
	}

	public int getOnLine() {
		return onLine;
	}

	public void setOnLine(int onLine) {
		this.onLine = onLine;
	}

	public String getUnfixedValue() {
		return unfixedValue;
	}

	public void setUnfixedValue(String unfixedValue) {
		this.unfixedValue = unfixedValue;
	}

	public String getUnfixedKey() {
		return unfixedKey;
	}

	public void setUnfixedKey(String unfixedKey) {
		this.unfixedKey = unfixedKey;
	}

	public String getUnfixedValue1() {
		return unfixedValue1;
	}

	public void setUnfixedValue1(String unfixedValue1) {
		this.unfixedValue1 = unfixedValue1;
	}

	public String getUnfixedKey1() {
		return unfixedKey1;
	}

	public void setUnfixedKey1(String unfixedKey1) {
		this.unfixedKey1 = unfixedKey1;
	}

	public String getUnfixedValue2() {
		return unfixedValue2;
	}

	public void setUnfixedValue2(String unfixedValue2) {
		this.unfixedValue2 = unfixedValue2;
	}

	public String getUnfixedKey2() {
		return unfixedKey2;
	}

	public void setUnfixedKey2(String unfixedKey2) {
		this.unfixedKey2 = unfixedKey2;
	}

	public String getUnfixedValue3() {
		return unfixedValue3;
	}

	public void setUnfixedValue3(String unfixedValue3) {
		this.unfixedValue3 = unfixedValue3;
	}

	public String getUnfixedKey3() {
		return unfixedKey3;
	}

	public void setUnfixedKey3(String unfixedKey3) {
		this.unfixedKey3 = unfixedKey3;
	}

	public Company_Information getCompanyInformation() {
		return companyInformation;
	}

	public void setCompanyInformation(Company_Information companyInformation) {
		this.companyInformation = companyInformation;
	}

	public Drone_Driver_Info getDroneDriverInfo() {
		return droneDriverInfo;
	}

	public void setDroneDriverInfo(Drone_Driver_Info droneDriverInfo) {
		this.droneDriverInfo = droneDriverInfo;
	}

}
