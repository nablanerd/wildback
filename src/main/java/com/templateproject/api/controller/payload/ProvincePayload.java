package com.templateproject.api.controller.payload;
/**
 * *
 * @author smaile
 *
 */
public class ProvincePayload {
	
	private String name;
	private int population;
	private int playerID;
	
	
	public ProvincePayload() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
}
