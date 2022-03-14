package br.com.ialmeida.projetofinaldesenvolvimentoweb.controllers;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.RebelService;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(RebelService rebelService, ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> report() {
        HashMap<String, Object> obj = reportService.report();
        return ResponseEntity.ok().body(obj);
    }

}
