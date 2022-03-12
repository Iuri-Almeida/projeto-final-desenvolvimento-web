package br.com.ialmeida.projetofinaldesenvolvimentoweb.entities;

import java.io.Serializable;

public class Localization implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double lat;
    private Double lon;
    private String galaxyName;

    public Localization() {
    }

    public Localization(Double lat, Double lon, String galaxyName) {
        this.lat = lat;
        this.lon = lon;
        this.galaxyName = galaxyName;
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
}
