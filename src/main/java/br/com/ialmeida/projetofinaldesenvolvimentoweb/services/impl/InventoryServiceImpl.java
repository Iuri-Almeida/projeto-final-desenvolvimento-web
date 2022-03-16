package br.com.ialmeida.projetofinaldesenvolvimentoweb.services.impl;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.InventoryRepository;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.InventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory insert(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
