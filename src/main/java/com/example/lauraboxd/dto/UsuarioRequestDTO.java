package com.example.lauraboxd.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioRequestDTO {


    @NotBlank(message = "o nome é obrigatorio.")
    private String nome;

    @NotBlank(message = "o email é obrigatorio.")
    @Email(message = "email inválido.")
    private String email;

    @Size(min = 6, max = 255)
    @NotBlank(message = "a senha é obrigatoria.")
    private String senha;


}
