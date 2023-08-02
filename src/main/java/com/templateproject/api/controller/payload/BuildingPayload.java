package com.templateproject.api.controller.payload;
/**
 * *
 * @author smaile
 *
 */
public class BuildingPayload {
	private String name;
	private String buildingtype;
	private String technology;
	private int provinceID;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuildingtype() {
		return buildingtype;
	}
	public void setBuildingtype(String buildingtype) {
		this.buildingtype = buildingtype;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public int getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}
	
	

}
