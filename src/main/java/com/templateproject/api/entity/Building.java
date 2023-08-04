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
 * 
 * @author smaile
 *
 */

@Entity
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;

	private String type;
	private int strength;
	private int wood;
	private int food;
	private int water;
	private int money;
	private int troop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;

	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	

	public int getTroop() {
		return troop;
	}

	public void setTroop(int troop) {
		this.troop = troop;
	}

	@JsonIgnore
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	//
	// @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "technology_id_id", referencedColumnName = "id")
	// private Technology technology;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name="buildingtype_id")
	// public Technology getTechnology() {
	// return technology;
	// }
	// public void setTechnology(Technology technology) {
	// this.technology = technology;
	// }
	// public BuildingType getBuildingtype() {
	// return buildingtype;
	// }
	// public void setBuildingtype(BuildingType buildingtype) {
	// this.buildingtype = buildingtype;
	// }
}
