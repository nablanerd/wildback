package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
/**
 * *
 * @author smaile
 *
 */
@Entity
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 
	 @OneToOne
	 @JoinColumn(name="province_id")
	 private Province province;
	 
	 
	 private int wood;
	 private int water;
	 private int food;
	 
	 public Resource() {}
	 
	 public Resource(int wood, int water, int food) {
		 this.wood = wood;
		 this.water = water;
		 this.food = food;
	 }

	 /*
	 public int getMoney() {
		return 2*wood + 3*food + 5*water;
	}*/

	public int getId() {
		 return id;
	 }
	 
	public void setId(int id) {
		this.id = id;
	}

	 public int getWood() {
		return wood;
	}
	
	 public void setWood(int wood) {
		this.wood = wood;
	}
	
	public int getWater() {
		return water;
	}
	
	public void setWater(int water) {
		this.water = water;
	}
	public void setFood(int food) {
		this.food = food;
	}
	
	public int getFood() {
		return food;
	}

	@JsonIgnore
	public Province getProvince() {
		return province;
	}
	
	public void setProvince(Province province) {
		this.province = province;
	} 
}
