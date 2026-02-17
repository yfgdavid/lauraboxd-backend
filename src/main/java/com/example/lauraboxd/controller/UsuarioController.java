package com.example.lauraboxd.controller;

import com.example.lauraboxd.dto.UsuarioResponseDTO;
import com.example.lauraboxd.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

   @Autowired
   private UsuarioService usuarioService;

    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(Authentication authentication) {
        usuarioService.deletarUsuario(authentication);
        return (ResponseEntity.noContent().build());
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(Authentication authentication) {
        return usuarioService.buscarUsuariosPorId(authentication)
                .map(usuario -> {
                    UsuarioResponseDTO dto = UsuarioResponseDTO.fromModel(usuario);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.buscarUsuarios()
                .stream()
                .map(UsuarioResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }
}
