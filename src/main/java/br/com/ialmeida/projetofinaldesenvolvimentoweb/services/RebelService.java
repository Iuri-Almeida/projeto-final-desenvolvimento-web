package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos.RebelDTO;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.RebelRepository;
import org.springframework.stereotype.Service;

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

    public Rebel fromRebelDTO(RebelDTO rebelDTO) {
        return new Rebel(rebelDTO.getId(), rebelDTO.getName(), rebelDTO.getAge(), rebelDTO.getGender(), rebelDTO.getLocalization(), rebelDTO.getInventory());
    }

}
