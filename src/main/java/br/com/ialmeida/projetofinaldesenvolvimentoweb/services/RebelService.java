package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos.RebelDTO;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RebelService {

    private final RebelRepository rebelRepository;

    public RebelService(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public List<RebelDTO> findAll() {
        List<Rebel> rebels = rebelRepository.findAll();
        List<RebelDTO> rebelDTOS = rebels.stream().map(RebelDTO::new).collect(Collectors.toList());;
        return rebelDTOS;
    }

    public RebelDTO findById(Long id) {
        Rebel rebel = rebelRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find rebel with id = " + id));
        return new RebelDTO(rebel);
    }

}
