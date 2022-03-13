package br.com.ialmeida.projetofinaldesenvolvimentoweb.controllers;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.RebelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rebels")
public class RebelController {

    private final RebelService rebelService;

    public RebelController(RebelService rebelService) {
        this.rebelService = rebelService;
    }

    @GetMapping
    public ResponseEntity<List<Rebel>> findAll() {
        return ResponseEntity.ok().body(rebelService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Rebel> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(rebelService.findById(id));
    }

}
