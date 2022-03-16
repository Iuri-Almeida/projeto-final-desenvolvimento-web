package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

public interface InventoryService {

    Inventory insert(Inventory inventory);

}
