package br.com.ialmeida.projetofinaldesenvolvimentoweb.entities;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.enums.Gender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rebel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
    private Localization localization;
    private final Map<String, Integer> inventory = new HashMap<>();

    public Rebel() {
    }

    public Rebel(Long id, String name, Integer age, Gender gender, Localization localization) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.localization = localization;

        this.inventory.put("FOOD", 0);
        this.inventory.put("WATER", 0);
        this.inventory.put("AMMUNITION", 0);
        this.inventory.put("GUN", 0);
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

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rebel rebel = (Rebel) o;
        return id.equals(rebel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
