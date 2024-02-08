package br.com.curso.joaociardullo.webfluxcourse.controller.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionsHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicateKeyException(
            DuplicateKeyException ex, ServerHttpRequest request
    ) {
        return ResponseEntity.badRequest().body(Mono.just(StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(verifyDupKey(ex.getMessage()))
                .path(request.toString())
                .build()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Mono<ValidationError>> validationError(
            WebExchangeBindException ex, ServerHttpRequest request) {

        ValidationError error = new ValidationError(LocalDateTime.now(), request.getPath().toString(),
                HttpStatus.BAD_REQUEST.value(), "Validation error", "error on validation  attributes");

        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            error.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(error));
    }

    private String verifyDupKey(String message) {
        if (message.contains("email dup key")) {
            return "E-mail already registered";
        }
        return "Dup Key exception";
    }
}
