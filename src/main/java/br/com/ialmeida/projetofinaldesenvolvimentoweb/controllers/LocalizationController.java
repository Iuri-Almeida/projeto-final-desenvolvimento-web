package br.com.ialmeida.projetofinaldesenvolvimentoweb.controllers;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos.RebelDTO;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.RebelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/localization")
public class LocalizationController {

    private final RebelService rebelService;

    public LocalizationController(RebelService rebelService) {
        this.rebelService = rebelService;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RebelDTO> update(@PathVariable Long id, @RequestBody RebelDTO rebelDTO) {
        Rebel rebel = rebelService.fromRebelDTO(rebelDTO);
        rebel = rebelService.updateRebelLocalization(id, rebel);

        return ResponseEntity.ok().body(new RebelDTO(rebel));
    }

}
