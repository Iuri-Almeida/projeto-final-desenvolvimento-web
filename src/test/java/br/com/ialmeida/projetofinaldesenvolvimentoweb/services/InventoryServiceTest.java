package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.InventoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testing Inventory Service")
class InventoryServiceTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    private InventoryService inventoryService;

    @BeforeEach
    public void init() {
        inventoryService = new InventoryService(inventoryRepository);
    }

    @Test
    @DisplayName("Verify if repository is null")
    void inventoryRepositoryIsNotNull() {
        assertNotNull(inventoryRepository);
    }

    @Test
    @DisplayName("Verify if service is null")
    void inventoryServiceIsNotNull() {
        assertNotNull(inventoryService);
    }

    @Test
    @DisplayName("Insert inventory when successful")
    void insertInventoryWhenSuccessful() {
        Inventory inventoryCreated = createInventory();

        Inventory inventorySaved = inventoryService.insert(inventoryCreated);

        Assertions.assertThat(inventorySaved).isNotNull();
        Assertions.assertThat(inventorySaved.getId()).isNotNull();
        Assertions.assertThat(inventorySaved.getFood()).isNotNull();
        Assertions.assertThat(inventorySaved.getWater()).isNotNull();
        Assertions.assertThat(inventorySaved.getAmmunition()).isNotNull();
        Assertions.assertThat(inventorySaved.getGun()).isNotNull();
    }

    private Inventory createInventory() {
        return new Inventory(null, 1, 2, 3, 4);
    }
}