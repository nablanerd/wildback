package com.templateproject.api.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
/**
 * *
 * @author smaile
 *
 */
@Entity
public class Battle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
    @Column(name = "name",unique = true )
	private String name;
	
	public Battle() {}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "battle")
	private List<Troop> troops;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "battle")
	private List<Player> players;
	@OneToOne
	private Player deffender;
	@OneToOne
	private Player attaquant;
	
	
	public Player getDeffender() {
		return deffender;
	}
	public void setDeffender(Player deffender) {
		this.deffender = deffender;
	}
	public Player getAttaquant() {
		return attaquant;
	}
	public void setAttaquant(Player attaquant) {
		this.attaquant = attaquant;
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
	public List<Troop> getTroops() {
		return troops;
	}
	public void setTroops(List<Troop> troops) {
		this.troops = troops;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
