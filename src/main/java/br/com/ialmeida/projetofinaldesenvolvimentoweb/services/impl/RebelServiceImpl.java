package br.com.ialmeida.projetofinaldesenvolvimentoweb.services.impl;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos.RebelDTO;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.RebelRepository;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.RebelService;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.exceptions.StarWarsException;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.exceptions.RebelNotFoundException;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.utils.ApiUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class RebelServiceImpl implements RebelService {

    private final RebelRepository rebelRepository;

    public RebelServiceImpl(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public List<Rebel> findAll() {
        return rebelRepository.findAll();
    }

    public Rebel findById(Long id) {
        return rebelRepository.findById(id).orElseThrow(() -> new RebelNotFoundException("Rebel not found. Id = " + id));
    }

    public Rebel findByName(String name) {
        name = ApiUtil.decodeParam(name);
        String finalName = name;
        return rebelRepository.findByNameContainingIgnoreCase(name).orElseThrow(() -> new RebelNotFoundException("Rebel not found. Name = " + finalName));
    }

    public Rebel insert(Rebel rebel) {
        rebel.setName(ApiUtil.stripAccents(rebel.getName()));
        return rebelRepository.save(rebel);
    }

    public Rebel updateRebelLocalization(Long id, Localization obj) {
        Rebel entity = findById(id);

        if (obj.getLat() != null) {
            entity.getLocalization().setLat(obj.getLat());
        }

        if (obj.getLon() != null) {
            entity.getLocalization().setLon(obj.getLon());
        }

        if (obj.getGalaxyName() != null) {
            entity.getLocalization().setGalaxyName(obj.getGalaxyName());
        }

        return rebelRepository.save(entity);
    }

    public HashMap<String, Object> apiReport() {
        List<Rebel> rebelList = findAll();

        int total = rebelList.size();
        int traitors = 0;
        int rebels = 0;

        int food = 0;
        int water = 0;
        int ammunition = 0;
        int gun = 0;

        int pointsLostByTraitors = 0;

        for (Rebel rebel : rebelList) {
            if (rebel.isTraitor()) {
                traitors++;
                pointsLostByTraitors += (rebel.getInventory().getFood() * ApiUtil.FOOD_SCORE) +
                        (rebel.getInventory().getWater() * ApiUtil.WATER_SCORE) +
                        (rebel.getInventory().getAmmunition() * ApiUtil.AMMUNITION_SCORE) +
                        (rebel.getInventory().getGun() * ApiUtil.GUN_SCORE);
            } else {
                rebels++;
                food += rebel.getInventory().getFood();
                water += rebel.getInventory().getWater();
                ammunition += rebel.getInventory().getAmmunition();
                gun += rebel.getInventory().getGun();
            }
        }

        double percentageOfRebels = (double) rebels / total;
        double percentageOfTraitors = (double) traitors / total;

        double avgFood = (double) food / rebels;
        double avgWater = (double) water / rebels;
        double avgAmmunition = (double) ammunition / rebels;
        double avgGun = (double) gun / rebels;

        HashMap<String, Object> obj = new HashMap<>();
        HashMap<String, Double> resourceAveragePerRebel = new HashMap<>();

        resourceAveragePerRebel.put("avgFood", Math.round(avgFood * 100.0) / 100.0);
        resourceAveragePerRebel.put("avgWater", Math.round(avgWater * 100.0) / 100.0);
        resourceAveragePerRebel.put("avgAmmunition", Math.round(avgAmmunition * 100.0) / 100.0);
        resourceAveragePerRebel.put("avgGun", Math.round(avgGun * 100.0) / 100.0);

        obj.put("percentageOfRebels", percentageOfRebels * 100);
        obj.put("percentageOfTraitors", percentageOfTraitors * 100);
        obj.put("resourceAveragePerRebel", resourceAveragePerRebel);
        obj.put("pointsLostByTraitors", pointsLostByTraitors);

        return obj;
    }

    public void reportRebelById(Long rebelId1, Long rebelId2) {
        reportRebel(findById(rebelId1), findById(rebelId2));
    }

    public void reportRebelByName(String rebelName1, String rebelName2) {
        reportRebel(findByName(rebelName1), findByName(rebelName2));
    }

    private void reportRebel(Rebel fromRebel, Rebel toRebel) {
        if (fromRebel.equals(toRebel)) {
            throw new StarWarsException("A rebel cannot report himself.");
        }

        if (fromRebel.getReportedRebels().contains(toRebel)) {
            throw new StarWarsException("This rebel has already reported the other one.");
        }

        fromRebel.getReportedRebels().add(toRebel);
        rebelRepository.save(fromRebel);

        toRebel.addReports();

        if (toRebel.getReports() == 3) {
            toRebel.setTraitor(true);
        }

        rebelRepository.save(toRebel);
    }

    public void tradeItemsById(Long rebelId1, Inventory items1, Long rebelId2, Inventory items2) {
        tradeItems(findById(rebelId1), items1, findById(rebelId2), items2);
    }

    public void tradeItemsByName(String rebelName1, Inventory items1, String rebelName2, Inventory items2) {
        tradeItems(findByName(rebelName1), items1, findByName(rebelName2), items2);
    }

    public void tradeItems(Rebel rebel1, Inventory items1, Rebel rebel2, Inventory items2) {
        validateTrade(rebel1, rebel2);

        // validate inventory
        validateInventory(rebel1, items1);
        validateInventory(rebel2, items2);

        // validate score
        validateScore(items1, items2);

        // confirm trade
        trade(rebel1, items1, items2);
        trade(rebel2, items2, items1);

        rebelRepository.saveAll(Arrays.asList(rebel1, rebel2));
    }

    private void validateTrade(Rebel rebel1, Rebel rebel2) {
        if (rebel1.equals(rebel2)) {
            throw new StarWarsException("A rebel cannot trade with himself.");
        }

        if (rebel1.isTraitor() || rebel2.isTraitor()) {
            throw new StarWarsException("Cannot have dealings with traitors.");
        }
    }

    private void validateInventory(Rebel rebel, Inventory items) {
        if (items.getFood() == null && items.getWater() == null && items.getAmmunition() == null && items.getGun() == null) {
            throw new StarWarsException("You must indicate at least one item from " + rebel.getName() + "'s inventory.");
        } else if (items.getFood() != null && rebel.getInventory().getFood() < items.getFood()) {
            throw new StarWarsException("To negotiate " + rebel.getName() + "'s FOOD you need to have the minimum necessary.");
        } else if (items.getWater() != null && rebel.getInventory().getWater() < items.getWater()) {
            throw new StarWarsException("To negotiate " + rebel.getName() + "'s WATER you need to have the minimum necessary.");
        } else if (items.getAmmunition() != null && rebel.getInventory().getAmmunition() < items.getAmmunition()) {
            throw new StarWarsException("To negotiate " + rebel.getName() + "'s AMMUNITION you need to have the minimum necessary.");
        } else if (items.getGun() != null && rebel.getInventory().getGun() < items.getGun()) {
            throw new StarWarsException("To negotiate " + rebel.getName() + "'s GUN you need to have the minimum necessary.");
        }
    }

    private void validateScore(Inventory items1, Inventory items2) {
        int score1 = getScore(items1);
        int score2 = getScore(items2);

        if (score1 != score2) {
            throw new StarWarsException("Both side must offer the same amount of points.");
        }
    }

    private int getScore(Inventory inventory) {
        int score = 0;

        score += (inventory.getFood() == null) ? 0 : inventory.getFood() * ApiUtil.FOOD_SCORE;
        score += (inventory.getWater() == null) ? 0 : inventory.getWater() * ApiUtil.WATER_SCORE;
        score += (inventory.getAmmunition() == null) ? 0 : inventory.getAmmunition() * ApiUtil.AMMUNITION_SCORE;
        score += (inventory.getGun() == null) ? 0 : inventory.getGun() * ApiUtil.GUN_SCORE;

        return score;
    }

    private void trade(Rebel rebel, Inventory myItems, Inventory otherItems) {
        setItems(rebel, myItems, true);
        setItems(rebel, otherItems, false);
    }

    private void setItems(Rebel rebel, Inventory inventory, boolean isRemoving) {
        if (inventory.getFood() != null) {
            rebel.getInventory().setFood(rebel.getInventory().getFood() + (isRemoving ? - inventory.getFood() : inventory.getFood()));
        }
        if (inventory.getWater() != null) {
            rebel.getInventory().setWater(rebel.getInventory().getWater() + (isRemoving ? - inventory.getWater() : inventory.getWater()));
        }
        if (inventory.getAmmunition() != null) {
            rebel.getInventory().setAmmunition(rebel.getInventory().getAmmunition() + (isRemoving ? - inventory.getAmmunition() : inventory.getAmmunition()));
        }
        if (inventory.getGun() != null) {
            rebel.getInventory().setGun(rebel.getInventory().getGun() + (isRemoving ? - inventory.getGun() : inventory.getGun()));
        }
    }

    public Rebel fromRebelDTO(RebelDTO rebelDTO) {
        return new Rebel(rebelDTO.getId(), rebelDTO.getName(), rebelDTO.getAge(), rebelDTO.getGender(), rebelDTO.getLocalization(), rebelDTO.getInventory());
    }

}
