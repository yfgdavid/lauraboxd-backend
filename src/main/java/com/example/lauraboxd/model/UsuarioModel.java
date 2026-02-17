package com.example.lauraboxd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank(message = "o nome é obrigatorio.")
    private String nome;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(message = "o email é obrigatorio.")
    @Email(message = "email inválido.")
    private String email;

    @Column(name = "senha", nullable = false, length = 255)
    @Size(min = 6, max = 255)
    @NotBlank(message = "a senha é obrigatoria.")
    private String senha;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}
