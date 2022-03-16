package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.enums.Gender;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.RebelRepository;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.impl.RebelServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
@DisplayName("Testing Rebel Service")
class RebelServiceTest {

    @Autowired
    private RebelRepository rebelRepository;

    private RebelServiceImpl rebelService;

    @BeforeEach
    void init() {
        rebelService = new RebelServiceImpl(rebelRepository);
    }

    @Test
    @DisplayName("Verify if repository is null")
    void rebelRepositoryIsNotNull() {
        Assertions.assertThat(rebelRepository).isNotNull();
    }

    @Test
    @DisplayName("Verify if service is null")
    void rebelServiceIsNotNull() {
        Assertions.assertThat(rebelService).isNotNull();
    }

    @Test
    @DisplayName("Find all Rebels when successful")
    void findAllRebelsWhenSuccessful() {
        List<Rebel> rebels = rebelService.findAll();

        Assertions.assertThat(rebels).isNotNull();
    }

    @Test
    @DisplayName("Find rebel by id when successful")
    void findById() {
        Rebel rebelCreated = insertRebel();

        Rebel rebelSaved = rebelService.findById(rebelCreated.getId());

        Assertions.assertThat(rebelSaved).isNotNull();
        Assertions.assertThat(Objects.equals(rebelCreated.getId(), rebelSaved.getId())).isTrue();
    }

    @Test
    @DisplayName("Insert rebel when successful")
    void insertRebelWhenSuccessful(){
        Rebel rebelSaved = insertRebel();

        Assertions.assertThat(rebelSaved).isNotNull();
        Assertions.assertThat(rebelSaved.getId()).isNotNull();
        Assertions.assertThat(rebelSaved.getName()).isNotNull();
        Assertions.assertThat(rebelSaved.getAge()).isNotNull();
        Assertions.assertThat(rebelSaved.getGender()).isNotNull();
        Assertions.assertThat(rebelSaved.getLocalization()).isNotNull();
        Assertions.assertThat(rebelSaved.getInventory()).isNotNull();
    }

    @Test
    @DisplayName("Update rebel localization when successful")
    void updateRebelLocalization() {
        Rebel rebel1 = insertRebel();
        Localization localization = createLocalization(98.76, 54.32, "Via Láctea");

        Rebel rebelModified = rebelService.updateRebelLocalization(rebel1.getId(), localization);

        Assertions.assertThat(rebelModified).isNotNull();
        Assertions.assertThat(rebelModified.getId()).isNotNull();
        Assertions.assertThat(Objects.equals(rebelModified.getId(), rebel1.getId())).isTrue();
        Assertions.assertThat(Objects.equals(rebelModified.getLocalization(), rebel1.getLocalization())).isTrue();
    }

    @Test
    @DisplayName("Report status when successful")
    void apiReportStatusWhenSuccessful() {
        HashMap<String, Object> obj = rebelService.apiReport();

        Assertions.assertThat(obj).isNotNull();
        Assertions.assertThat(obj.size() == 4).isTrue();
    }

    private Rebel insertRebel() {
        Localization localizationCreated = createLocalization(12.34, 56.78, "Andromeda");
        Inventory inventoryCreated = createInventory(1, 2, 3, 4);
        Rebel rebelCreated = createRebel("Iuri", 22, Gender.MALE, localizationCreated, inventoryCreated);

        return rebelService.insert(rebelCreated);
    }

    private List<Rebel> getAllRebels() {
        return new ArrayList<>(Arrays.asList(
                createRebel("Iuri", 22, Gender.MALE, createLocalization(23.45, 67.89, "Andromeda"), createInventory(1, 2, 3, 4)),
                createRebel("Rafela", 34, Gender.FEMALE, createLocalization(12.34, 56.78, "Galáxia do Triângulo"), createInventory(1, 3, 6, 3)),
                createRebel("Fernando", 54, Gender.MALE, createLocalization(98.76, 54.32, "Via Láctea"), createInventory(3, 0, 0, 0))
        ));
    }

    private Rebel createRebel(String name, Integer age, Gender gender, Localization localization, Inventory inventory) {
        return new Rebel(null, name, age, gender, localization, inventory);
    }

    private Localization createLocalization(Double lat, Double lon, String galaxyName) {
        return new Localization(null, lat, lon, galaxyName);
    }

    private Inventory createInventory(Integer food, Integer water, Integer ammunition, Integer gun) {
        return new Inventory(null, food, water, ammunition, gun);
    }
}