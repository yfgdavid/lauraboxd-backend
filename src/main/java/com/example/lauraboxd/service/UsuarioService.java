package com.example.lauraboxd.service;

import com.example.lauraboxd.dto.UsuarioRequestDTO;
import com.example.lauraboxd.model.UsuarioModel;
import com.example.lauraboxd.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioModel salvarUsuario(UsuarioRequestDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail()))  {
            throw new RuntimeException("Email ja cadastrado");
        }
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setEmail(usuarioDTO.getEmail());

        return usuarioRepository.save(usuario);
    }

   public List<UsuarioModel> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> buscarUsuariosPorId(Authentication  authentication) {
        UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuarioRepository.findById(usuario.getId());
    }

    public void deletarUsuario(Authentication  authentication) {
        UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new RuntimeException("Usuario não encontrado.");
        }
        usuarioRepository.deleteById(usuario.getId());
    }

}
