package com.example.lauraboxd.controller;


import com.example.lauraboxd.dto.LoginDTO;
import com.example.lauraboxd.dto.UsuarioRequestDTO;
import com.example.lauraboxd.dto.UsuarioResponseDTO;
import com.example.lauraboxd.model.UsuarioModel;
import com.example.lauraboxd.repository.UsuarioRepository;
import com.example.lauraboxd.security.JwtUtil;
import com.example.lauraboxd.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            UsuarioModel usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
            if  (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
                return ResponseEntity.status(401).body("Credenciais Invalidas");
            }

            String token = jwtUtil.generateToken(loginDTO.getEmail());

            Map<String,Object> response = new HashMap<>();
            response.put("token", token);
            response.put("usuario", UsuarioResponseDTO.fromModel(usuario));
            response.put("message", "Login realizado com sucesso.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar o login." + e.getMessage());

        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UsuarioRequestDTO usuarioDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                erros.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body(Map.of(
                    "sucesso", false,
                    "mensagem", "Validação falhou",
                    "erros", erros));
        }

        try {
            UsuarioModel usuario = usuarioService.salvarUsuario(usuarioDTO);
            return ResponseEntity.status(201).body(Map.of(
                    "sucesso", true,
                    "mensagem", "Usuário registrado com sucesso",
                    "usuario", UsuarioResponseDTO.fromModel(usuario)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "sucesso", false,
                    "mensagem", e.getMessage()));
        }
    }


}
