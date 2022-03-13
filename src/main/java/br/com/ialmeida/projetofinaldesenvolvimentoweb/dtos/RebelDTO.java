package br.com.ialmeida.projetofinaldesenvolvimentoweb.dtos;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.enums.Gender;

import java.io.Serializable;

public class RebelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
    private Localization localization;
    private Inventory inventory;

    public RebelDTO() {
    }

    public RebelDTO(Rebel rebel) {
        id = rebel.getId();
        name = rebel.getName();
        age = rebel.getAge();
        gender = rebel.getGender();
        localization = rebel.getLocalization();
        inventory = rebel.getInventory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
