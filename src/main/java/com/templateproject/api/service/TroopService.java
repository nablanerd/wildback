package com.templateproject.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.templateproject.api.entity.Battle;
import com.templateproject.api.entity.Troop;
import com.templateproject.api.repository.TroopRepository;
/**
 * *
 * @author smaile
 *
 */
@Service
public class TroopService {

    private final TroopRepository troopRepository;

    public TroopService(TroopRepository troopRepository) {
        this.troopRepository = troopRepository;
    }

    public void addTroop(String name, Battle battle) {
        Troop troop = new Troop();
        troop.setName(name);
        troop.setBattle(battle);
        troopRepository.save(troop);
    }

    public Troop getById(int id) {
        Optional<Troop> optionalTroop = troopRepository.findById(id);
        return optionalTroop.orElse(null);
    }

    public Troop getByName(String name) {
        return troopRepository.findByName(name);
    }

    public List<Troop> getAllTroops() {
        return troopRepository.findAll();
    }

    public void updateTroop(int id, String name, Battle battle) {
        Troop troop = getById(id);
        if (troop != null) {
            troop.setName(name);
            troop.setBattle(battle);
            troopRepository.save(troop);
        }
    }

    public void deleteTroop(int id) {
        troopRepository.deleteById(id);
    }
}
