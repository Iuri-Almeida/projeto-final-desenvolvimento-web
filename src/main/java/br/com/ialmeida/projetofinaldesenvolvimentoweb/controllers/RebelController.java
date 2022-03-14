package br.com.ialmeida.projetofinaldesenvolvimentoweb.controllers;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos.RebelDTO;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.InventoryService;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.LocalizationService;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.RebelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/rebels")
public class RebelController {

    private final RebelService rebelService;
    private final LocalizationService localizationService;
    private final InventoryService inventoryService;

    public RebelController(RebelService rebelService, LocalizationService localizationService, InventoryService inventoryService) {
        this.rebelService = rebelService;
        this.localizationService = localizationService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<RebelDTO>> findAll() {
        List<Rebel> rebels = rebelService.findAll();
        List<RebelDTO> rebelDTOS = rebels.stream().map(RebelDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(rebelDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RebelDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new RebelDTO(rebelService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody RebelDTO rebelDTO) {
        localizationService.insert(rebelDTO.getLocalization());
        inventoryService.insert(rebelDTO.getInventory());

        Rebel rebel = rebelService.fromRebelDTO(rebelDTO);
        rebel = rebelService.insert(rebel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rebel.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/reportTraitor")
    public ResponseEntity<RebelDTO> report(
            @RequestParam(value = "fromRebel", defaultValue = "") Long fromRebelId,
            @RequestParam(value = "toRebel", defaultValue = "") Long toRebelId) {
        rebelService.reportRebel(fromRebelId, toRebelId);
        return ResponseEntity.noContent().build();
    }

}
