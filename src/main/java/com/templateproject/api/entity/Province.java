package com.templateproject.api.entity;
/**
 * *
 * @author smaile
 *
 */
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
/**
 * *
 * @author smaile
 *
 */
@Entity
public class Province {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
    @Column(name = "name",unique = true )
	private String name;
	private int population;
	
	@OneToOne(mappedBy = "province")
	private Resource resources;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
	private List<Building> buildings;
	
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
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public Resource getResources() {
		return resources;
	}
	
	public void setResources(Resource resources) {
		this.resources = resources;
	}
	
	@JsonIgnore
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	
	
	
}
