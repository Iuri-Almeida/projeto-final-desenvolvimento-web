package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.util.HashMap;

@TestComponent
@DisplayName("Testing Report Service")
class ReportServiceTest {

    @Test
    @DisplayName("Report status when successful")
    void reportStatusWhenSuccessful() {
        HashMap<String, Object> obj = new HashMap<>();
        HashMap<String, Double> resourceAveragePerRebel = new HashMap<>();

        resourceAveragePerRebel.put("avgFood", Math.round(1.67 * 100.0) / 100.0);
        resourceAveragePerRebel.put("avgWater", Math.round(2 * 100.0) / 100.0);
        resourceAveragePerRebel.put("avgAmmunition", Math.round(1.67 * 100.0) / 100.0);
        resourceAveragePerRebel.put("avgGun", Math.round(1.67 * 100.0) / 100.0);

        obj.put("percentageOfRebels", 100);
        obj.put("percentageOfTraitors", 0);
        obj.put("resourceAveragePerRebel", resourceAveragePerRebel);
        obj.put("pointsLostByTraitors", 0);

        Assertions.assertThat(obj).isNotNull();
        Assertions.assertThat(obj).isNotEmpty();
        Assertions.assertThat(obj.get("percentageOfRebels")).isNotNull();
        Assertions.assertThat(obj.get("percentageOfTraitors")).isNotNull();
        Assertions.assertThat(obj.get("resourceAveragePerRebel")).isNotNull();
        Assertions.assertThat((HashMap<String, Double>) obj.get("resourceAveragePerRebel")).isNotEmpty();
        Assertions.assertThat(((HashMap<String, Double>) obj.get("resourceAveragePerRebel")).get("avgFood")).isNotNull();
        Assertions.assertThat(((HashMap<String, Double>) obj.get("resourceAveragePerRebel")).get("avgWater")).isNotNull();
        Assertions.assertThat(((HashMap<String, Double>) obj.get("resourceAveragePerRebel")).get("avgAmmunition")).isNotNull();
        Assertions.assertThat(((HashMap<String, Double>) obj.get("resourceAveragePerRebel")).get("avgGun")).isNotNull();
        Assertions.assertThat(obj.get("pointsLostByTraitors")).isNotNull();
    }

}