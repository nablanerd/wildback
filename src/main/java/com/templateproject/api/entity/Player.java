package com.templateproject.api.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
/**
 * *
 * @author smaile
 *
 */

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
    @Column(name = "username",unique = true )
	private String username;
	@NotNull
	@Column(name = "password")
	private String password;
	@NotNull
    @Column(name = "email",unique = true)
	private String email;
	//commentaire 
	@Column(name = "isConnected")
	private Boolean isConnected = false;
	
	
	

	@OneToMany(mappedBy = "player")
	private List<Province> provinces;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "battle_id")
	private Battle battle;
	
	
	 private int wood;
	 private int water;
	 private int food;
	 private int money;
	
	
	public Player() {}

	public Player( String username, String password, String email, List<Province> provinces, Battle battle) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.provinces = provinces;
		this.battle = battle;
	}
	
	public Player( String username, String password, String email ) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Province> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}
	public Battle getBattle() {
		return battle;
	}
	public void setBattle(Battle battle) {
		this.battle = battle;
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


	public int getMoney()
	{

return money;

	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", provinces=" + provinces + ", battle=" + battle + "]";
	}
	
	

}
