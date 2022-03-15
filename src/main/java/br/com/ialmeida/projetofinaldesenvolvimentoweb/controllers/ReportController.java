package br.com.ialmeida.projetofinaldesenvolvimentoweb.controllers;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.RebelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    private final RebelService rebelService;

    public ReportController(RebelService rebelService) {
        this.rebelService = rebelService;
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> report() {
        HashMap<String, Object> obj = rebelService.apiReport();
        return ResponseEntity.ok().body(obj);
    }

}
