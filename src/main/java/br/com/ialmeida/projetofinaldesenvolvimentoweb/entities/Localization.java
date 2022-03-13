package br.com.ialmeida.projetofinaldesenvolvimentoweb.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_localization")
public class Localization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double lat;
    private Double lon;
    private String galaxyName;

    @OneToOne(mappedBy = "localization")
    private Rebel rebel;

    public Localization() {
    }

    public Localization(Double lat, Double lon, String galaxyName) {
        this.lat = lat;
        this.lon = lon;
        this.galaxyName = galaxyName;
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
