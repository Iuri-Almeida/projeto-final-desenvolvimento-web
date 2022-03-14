package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos.RebelDTO;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.RebelRepository;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.utils.TradeConstants;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RebelService {

    private final RebelRepository rebelRepository;

    public RebelService(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public List<Rebel> findAll() {
        return rebelRepository.findAll();
    }

    public Rebel findById(Long id) {
        return rebelRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find rebel with id = " + id));
    }

    public Rebel insert(Rebel rebel) {
        return rebelRepository.save(rebel);
    }

    public Rebel updateRebelLocalization(Long id, Rebel obj) {
        Rebel entity = findById(id);

        entity.getLocalization().setLat(obj.getLocalization().getLat());
        entity.getLocalization().setLon(obj.getLocalization().getLon());
        entity.getLocalization().setGalaxyName(obj.getLocalization().getGalaxyName());

        return rebelRepository.save(entity);
    }

    public void reportRebel(Long fromRebelId, Long toRebelId) {
        if (fromRebelId.equals(toRebelId)) {
            throw new RuntimeException("A rebel cannot report himself.");
        }

        Rebel fromRebel = findById(fromRebelId);
        Rebel toRebel = findById(toRebelId);

        if (fromRebel.getReportedRebels().contains(toRebel)) {
            throw new RuntimeException("This rebel has already reported the other one.");
        }

        fromRebel.getReportedRebels().add(toRebel);
        rebelRepository.save(fromRebel);

        toRebel.addReports();

        if (toRebel.getReports() == 3) {
            toRebel.setTraitor(true);
        }

        rebelRepository.save(toRebel);
    }

    public void tradeItems(Long rebelId1, Inventory items1, Long rebelId2, Inventory items2) {
        Rebel rebel1 = findById(rebelId1);
        Rebel rebel2 = findById(rebelId2);

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
            throw new RuntimeException("A rebel cannot trade with himself.");
        }

        if (rebel1.isTraitor() || rebel2.isTraitor()) {
            throw new RuntimeException("Cannot have dealings with traitors.");
        }
    }

    private void validateInventory(Rebel rebel, Inventory items) {
        if (items.getFood() == null && items.getWater() == null && items.getAmmunition() == null && items.getGun() == null) {
            throw new RuntimeException("You must indicate at least one item from " + rebel.getName() + "'s inventory.");
        } else if (items.getFood() != null && rebel.getInventory().getFood() < items.getFood()) {
            throw new RuntimeException("To negotiate " + rebel.getName() + "'s FOOD you need to have the minimum necessary.");
        } else if (items.getWater() != null && rebel.getInventory().getWater() < items.getWater()) {
            throw new RuntimeException("To negotiate " + rebel.getName() + "'s WATER you need to have the minimum necessary.");
        } else if (items.getAmmunition() != null && rebel.getInventory().getAmmunition() < items.getAmmunition()) {
            throw new RuntimeException("To negotiate " + rebel.getName() + "'s AMMUNITION you need to have the minimum necessary.");
        } else if (items.getGun() != null && rebel.getInventory().getGun() < items.getGun()) {
            throw new RuntimeException("To negotiate " + rebel.getName() + "'s GUN you need to have the minimum necessary.");
        }
    }

    private void validateScore(Inventory items1, Inventory items2) {
        int score1 = getScore(items1);
        int score2 = getScore(items2);

        if (score1 != score2) {
            throw new RuntimeException("Both side must offer the same amount of points.");
        }
    }

    private int getScore(Inventory inventory) {
        int score = 0;

        score += (inventory.getFood() == null) ? 0 : inventory.getFood() * TradeConstants.FOOD_SCORE;
        score += (inventory.getWater() == null) ? 0 : inventory.getWater() * TradeConstants.WATER_SCORE;
        score += (inventory.getAmmunition() == null) ? 0 : inventory.getAmmunition() * TradeConstants.AMMUNITION_SCORE;
        score += (inventory.getGun() == null) ? 0 : inventory.getGun() * TradeConstants.GUN_SCORE;

        return score;
    }

    private void trade(Rebel rebel, Inventory myItems, Inventory otherItems) {
        removeItems(rebel, myItems);
        addNewItems(rebel, otherItems);
    }

    private void removeItems(Rebel rebel, Inventory inventory) {
        if (inventory.getFood() != null) {
            rebel.getInventory().setFood(rebel.getInventory().getFood() - inventory.getFood());
        }
        if (inventory.getWater() != null) {
            rebel.getInventory().setWater(rebel.getInventory().getWater() - inventory.getWater());
        }
        if (inventory.getAmmunition() != null) {
            rebel.getInventory().setAmmunition(rebel.getInventory().getAmmunition() - inventory.getAmmunition());
        }
        if (inventory.getGun() != null) {
            rebel.getInventory().setGun(rebel.getInventory().getGun() - inventory.getGun());
        }
    }

    private void addNewItems(Rebel rebel, Inventory inventory) {
        if (inventory.getFood() != null) {
            rebel.getInventory().setFood(rebel.getInventory().getFood() + inventory.getFood());
        }
        if (inventory.getWater() != null) {
            rebel.getInventory().setWater(rebel.getInventory().getWater() + inventory.getWater());
        }
        if (inventory.getAmmunition() != null) {
            rebel.getInventory().setAmmunition(rebel.getInventory().getAmmunition() + inventory.getAmmunition());
        }
        if (inventory.getGun() != null) {
            rebel.getInventory().setGun(rebel.getInventory().getGun() + inventory.getGun());
        }
    }

    public Rebel fromRebelDTO(RebelDTO rebelDTO) {
        return new Rebel(rebelDTO.getId(), rebelDTO.getName(), rebelDTO.getAge(), rebelDTO.getGender(), rebelDTO.getLocalization(), rebelDTO.getInventory());
    }

}
