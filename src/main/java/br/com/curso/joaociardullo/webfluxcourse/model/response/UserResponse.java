package br.com.curso.joaociardullo.webfluxcourse.model.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserResponse(

        @Size(min = 3, max = 50)
        @NotBlank(message = "must not be null or empty")
        String id,
        String name,

        @Email(message = "email invalid")
        String email,
        String password

){  }
