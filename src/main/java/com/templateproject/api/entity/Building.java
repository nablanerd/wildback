package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
/**
 * *
 * @author smaile
 *
 */

@Entity
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="province_id")
	private Province province;

	private TechnologyType technology;
	private BuildingType buildingtype;
	
	
	
	
	
	public TechnologyType getTechnology() {
		return technology;
	}


	public void setTechnology(TechnologyType technology) {
		this.technology = technology;
	}
	
	
	public BuildingType getBuildingtype() {
		return buildingtype;
	}
	public void setBuildingtype(BuildingType buildingtype) {
		this.buildingtype = buildingtype;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
//	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "technology_id_id", referencedColumnName = "id")
//	private Technology technology;
	
//	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="buildingtype_id")
//	public Technology getTechnology() {
//		return technology;
//	}
//	public void setTechnology(Technology technology) {
//		this.technology = technology;
//	}
//	public BuildingType getBuildingtype() {
//		return buildingtype;
//	}
//	public void setBuildingtype(BuildingType buildingtype) {
//		this.buildingtype = buildingtype;
//	}
}
