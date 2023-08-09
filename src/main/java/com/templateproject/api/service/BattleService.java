package com.templateproject.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.templateproject.api.entity.Battle;
import com.templateproject.api.entity.Province;
import com.templateproject.api.entity.Building;
import com.templateproject.api.entity.Player;
import com.templateproject.api.repository.BattleRepository;
import com.templateproject.api.repository.PlayerRepository;
import com.templateproject.api.repository.ProvinceRepository;

import java.util.Collections;
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
    private final ProvinceService  provinceService;
        private final ProvinceRepository provinceRepository;



    public BattleService(BattleRepository battleRepository, PlayerService playerService,PlayerRepository playerRepository,ProvinceService  provinceService, ProvinceRepository provinceRepository) {
        this.battleRepository = battleRepository;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.provinceService = provinceService;

        this.provinceRepository = provinceRepository;

    }

    public int getPoints(Integer idPlayer) {

		    //var player =  playerRepository.findById(idPlayer);
		
		    var points = 0;
		
		    for (Province province : provinceService.getProvinceByPlayerId(idPlayer)) {
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



  public List<HashMap<String, Object>> getRanking() {
      var payload = new ArrayList<HashMap<String, Object>>(); 

      List<Player> playerList = playerRepository.findByIsConnected(true);
      for (var player: playerList) {
          var newPlayer = new HashMap<String, Object>();
          newPlayer.put("login", player.getUsername());
          newPlayer.put("email", player.getEmail());
          newPlayer.put("id", Integer.toString(player.getId()));
        newPlayer.put("points", getPoints(player.getId()));


          payload.add(newPlayer);
    	 
      }

      Collections.sort(payload, new PlayerComparator());

      return payload;
  }
  

  public void transfertProvince(Player winner, Player loser)
  {

/* var  loserProvinces = loser.getProvinces();

Collections.shuffle(loserProvinces);

var firstProvince = loserProvinces.get(0);

winner.setProvinces( Arrays.asList(new Province[]{firstProvince}));
 */


var  loserProvinces = loser.getProvinces();
Collections.shuffle(loserProvinces);
var firstLoserProvince = loserProvinces.get(0);

firstLoserProvince.setPlayer(winner);

provinceRepository.save(firstLoserProvince);

playerRepository.save(loser);
playerRepository.save(winner);




  }


}

class PlayerComparator implements java.util.Comparator<HashMap<String, Object>> {
    @Override
    public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
        return (int)b.get("points") - (int)a.get("points");
    }
}