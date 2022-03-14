package br.com.ialmeida.projetofinaldesenvolvimentoweb.controllers;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.RebelService;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.utils.TradeConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    private final RebelService rebelService;

    public ReportController(RebelService rebelService) {
        this.rebelService = rebelService;
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> report() {
        List<Rebel> rebelList = rebelService.findAll();

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
                pointsLostByTraitors += (rebel.getInventory().getFood() * TradeConstants.FOOD_SCORE) +
                                  (rebel.getInventory().getWater() * TradeConstants.WATER_SCORE) +
                                  (rebel.getInventory().getAmmunition() * TradeConstants.AMMUNITION_SCORE) +
                                  (rebel.getInventory().getGun() * TradeConstants.GUN_SCORE);
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

        return ResponseEntity.ok().body(obj);
    }

}
