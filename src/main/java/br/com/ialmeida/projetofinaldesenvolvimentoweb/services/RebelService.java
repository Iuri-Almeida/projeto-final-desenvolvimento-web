package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos.RebelDTO;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface RebelService {

    List<Rebel> findAll();

    Rebel findById(Long id);

    Rebel findByName(String name);

    Rebel insert(Rebel rebel);

    Rebel updateRebelLocalization(Long id, Localization obj);

    HashMap<String, Object> apiReport();

    void reportRebelById(Long rebelId1, Long rebelId2);

    void reportRebelByName(String rebelName1, String rebelName2);

    void tradeItemsById(Long rebelId1, Inventory items1, Long rebelId2, Inventory items2);

    void tradeItemsByName(String rebelName1, Inventory items1, String rebelName2, Inventory items2);

    void tradeItems(Rebel rebel1, Inventory items1, Rebel rebel2, Inventory items2);

    Rebel fromRebelDTO(RebelDTO rebelDTO);

}
