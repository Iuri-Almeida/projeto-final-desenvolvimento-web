package br.com.ialmeida.projetofinaldesenvolvimentoweb.services.exceptions;

public class StarWarsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StarWarsException(String msg) {
        super(msg);
    }

}
