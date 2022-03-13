package br.com.ialmeida.projetofinaldesenvolvimentoweb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer food;
    private Integer water;
    private Integer ammunition;
    private Integer gun;

    @OneToOne(mappedBy = "inventory")
    @JsonIgnore
    private Rebel rebel;

    public Inventory() {
    }

    public Inventory(Long id, Integer food, Integer water, Integer ammunition, Integer gun) {
        this.id = id;
        this.food = food;
        this.water = water;
        this.ammunition = ammunition;
        this.gun = gun;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(Integer ammunition) {
        this.ammunition = ammunition;
    }

    public Integer getGun() {
        return gun;
    }

    public void setGun(Integer gun) {
        this.gun = gun;
    }

    public Rebel getRebel() {
        return rebel;
    }

    public void setRebel(Rebel rebel) {
        this.rebel = rebel;
    }
}
