package com.templateproject.api.controller.payload;

public class RessourcePayload {
		
	private int provinceID;
	private int wood;
	private int water;
	private int food;
	
	
	public RessourcePayload() {}
	
	public int getProvinceID() {
		return provinceID;
	}

	
	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
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

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

}
