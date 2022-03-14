package br.com.ialmeida.projetofinaldesenvolvimentoweb.controllers.exceptions;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.exceptions.StarWarsException;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.exceptions.RebelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RebelNotFoundException.class)
    public ResponseEntity<StandardError> rebelNotFound(RebelNotFoundException e, HttpServletRequest request) {
        String error = "Rebel not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(StarWarsException.class)
    public ResponseEntity<StandardError> apiError(StarWarsException e, HttpServletRequest request) {
        String error = "Api error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
