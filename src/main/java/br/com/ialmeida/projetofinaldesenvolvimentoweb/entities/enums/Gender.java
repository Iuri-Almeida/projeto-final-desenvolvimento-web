package br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.enums;

public enum Gender {

    MALE('M'),
    FEMALE('F');

    private final char code;

    private Gender(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Gender valueOf(char code) {
        for (Gender value : Gender.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Gender code.");
    }
}
