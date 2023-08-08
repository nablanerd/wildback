package com.templateproject.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.templateproject.api.entity.Battle;
import com.templateproject.api.entity.Province;
import com.templateproject.api.entity.Building;
import com.templateproject.api.repository.BattleRepository;
import com.templateproject.api.repository.PlayerRepository;
/**
 * *
 * @author smaile
 *
 */
@Service
public class BattleService {

    private final BattleRepository battleRepository;
    private final PlayerService playerService ;
    private final PlayerRepository playerRepository;
    private final ProvinceService  ProvinceService;

    public BattleService(BattleRepository battleRepository, PlayerService playerService,PlayerRepository playerRepository,ProvinceService  ProvinceService) {
        this.battleRepository = battleRepository;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.ProvinceService = ProvinceService;
    }

    public int getPoints(Integer idPlayer) {

		    //var player =  playerRepository.findById(idPlayer);
		
		    var points = 0;
		
		    for (Province province : ProvinceService.getProvinceByPlayerId(idPlayer)) {
			    for (Building building : province.getBuildings()) {
				    var power = building.getStrength() * building.getTroop();
				    		points += power;
				    	}
		   }
		return points;
    }
    
    
    public void add(Battle battle) {
        // TODO: Ajoutez ici les validations des paramètres
        battleRepository.save(battle);
    }

    public Battle getById(int id) throws Exception {
        Optional<Battle> battleOptional = battleRepository.findById(id);
        if (battleOptional.isPresent()) {
            return battleOptional.get();
        } else {
            throw new Exception("Battle with ID: " + id + " not found");
        }
    }

    public List<Battle> getAll() {
        return battleRepository.findAll();
    }

    public void update(int id, String name) throws Exception {
        Battle battle = getById(id);
        // TODO: Ajoutez ici les validations des paramètres
        battle.setName(name);
        battleRepository.save(battle);
    }

    public void delete(int id) throws Exception {
        Battle battle = getById(id);
        battleRepository.deleteById(battle.getId());
    }
    public Battle getByName(String name) throws Exception {
        Battle battle = battleRepository.findByName(name);
        if (battle != null) {
            return battle;
        } else {
            throw new Exception("Battle with name: " + name + " not found");
        }
    }
    
 // Méthode pour supprimer une bataille par son nom
    public void deleteByName(String name) {
        Battle battle = battleRepository.findByName(name);
        if (battle != null) {
            battleRepository.deleteById(battle.getId());
        }
    }
}
