package br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;

import java.io.Serializable;

public class LocalizationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Double lat;
    private Double lon;
    private String galaxyName;
    private Rebel rebel;

    public LocalizationDTO() {
    }

    public LocalizationDTO(Localization localization) {
        id = localization.getId();
        lat = localization.getLat();
        lon = localization.getLon();
        galaxyName = localization.getGalaxyName();
        rebel = localization.getRebel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getGalaxyName() {
        return galaxyName;
    }

    public void setGalaxyName(String galaxyName) {
        this.galaxyName = galaxyName;
    }

    public Rebel getRebel() {
        return rebel;
    }

    public void setRebel(Rebel rebel) {
        this.rebel = rebel;
    }
}
