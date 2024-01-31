package br.com.curso.joaociardullo.webfluxcourse.model.request;

public record UserRequest(
        String name,
        String email,
        String password
)
{}
