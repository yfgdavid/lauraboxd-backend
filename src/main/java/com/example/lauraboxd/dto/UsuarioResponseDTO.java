package com.example.lauraboxd.dto;

import com.example.lauraboxd.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;


    public static UsuarioResponseDTO fromModel(UsuarioModel usuario) {
        UsuarioResponseDTO usuarioDto = new UsuarioResponseDTO();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setDataCriacao(usuario.getDataCriacao());

        return usuarioDto;
    }

}
