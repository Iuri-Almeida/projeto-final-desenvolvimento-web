package br.com.ialmeida.projetofinaldesenvolvimentoweb.entities;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_rebel")
public class Rebel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Gender gender;

    @OneToOne
    private Localization localization;

    @OneToOne
    private Inventory inventory;

    public Rebel() {
    }

    public Rebel(Long id, String name, Integer age, Gender gender, Localization localization, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.localization = localization;
        this.inventory = inventory;
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
