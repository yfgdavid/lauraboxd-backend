package com.example.lauraboxd.service;


import com.example.lauraboxd.dto.ResenhaRequestDTO;
import com.example.lauraboxd.model.ResenhaModel;
import com.example.lauraboxd.model.UsuarioModel;
import com.example.lauraboxd.repository.ResenhaRepository;
import com.example.lauraboxd.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResenhaService {

    @Autowired
    private ResenhaRepository resenhaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public ResenhaModel criarConteudo(ResenhaRequestDTO resenhaDTO, Authentication authentication) {
        UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

       if (resenhaDTO.getTipoConteudo() == ResenhaModel.TipoConteudo.FILME ) {
           if (resenhaDTO.getTmdbId() == null) {
              throw new RuntimeException("Id do filme nao encontrado.");
           }
       } else if (resenhaDTO.getTipoConteudo() == ResenhaModel.TipoConteudo.SERIE) {
           if (resenhaDTO.getTmdbId() == null) {
               throw new RuntimeException("Id da serie nao encontrada.");
           }
       } else if (resenhaDTO.getTipoConteudo() == ResenhaModel.TipoConteudo.TEMPORADA) {
           if (resenhaDTO.getSerieTmdbId() == null || resenhaDTO.getTemporadaNumero() == null) {
               throw new RuntimeException("Não foi possivel fazer a resenha da temporada");
           }
       }


        if (resenhaDTO.getTipoConteudo() == ResenhaModel.TipoConteudo.FILME || resenhaDTO.getTipoConteudo() == ResenhaModel.TipoConteudo.SERIE) {
            ResenhaModel resenha = new ResenhaModel();
            resenha.setTmdbId(resenhaDTO.getTmdbId());
            resenha.setNota(resenhaDTO.getNota());
            resenha.setComentario(resenhaDTO.getComentario());
            resenha.setUsuario(usuario);
            resenha.setTipoConteudo(resenhaDTO.getTipoConteudo());
            resenha.setTemporadaNumero(null);
            resenha.setSerieTmdbId(null);

            return resenhaRepository.save(resenha);
        }  else if (resenhaDTO.getTipoConteudo() == ResenhaModel.TipoConteudo.TEMPORADA) {
            ResenhaModel resenha = new ResenhaModel();
            resenha.setTmdbId(null);
            resenha.setNota(resenhaDTO.getNota());
            resenha.setComentario(resenhaDTO.getComentario());
            resenha.setUsuario(usuario);
            resenha.setTipoConteudo(resenhaDTO.getTipoConteudo());
            resenha.setTemporadaNumero(resenhaDTO.getTemporadaNumero());
            resenha.setSerieTmdbId(resenhaDTO.getSerieTmdbId());

            return resenhaRepository.save(resenha);
        }
        throw new RuntimeException("conteudo nao encontrado.");}

    public List<ResenhaModel> listarConteudos(Authentication authentication) {
        UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return resenhaRepository.findAllByUsuarioId(usuario.getId());
    }

    public Optional<ResenhaModel> listarConteudosPorid(Long idResenha, Authentication authentication) {
        UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName()) .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Optional<ResenhaModel> resenhaOpt = resenhaRepository.findByIdResenhaAndUsuarioId(idResenha, usuario.getId());
        if (resenhaOpt.isEmpty()) {
            throw new RuntimeException("Conteudo nao encontrado");
        }
        return (resenhaOpt);
    }

    public void deletarConteudosPorId(Long idResenha, Authentication authentication) {
        UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName()) .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!resenhaRepository.findByIdResenhaAndUsuarioId(idResenha, usuario.getId()).isPresent()) {
            throw new RuntimeException("Conteudo nao encontrado");
        }
        resenhaRepository.deleteById(idResenha);
    }

    public List<ResenhaModel> buscarPorUsuario(Authentication authentication) {
        UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName()) .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return resenhaRepository.findByUsuarioId(usuario.getId());
    }

    public List<ResenhaModel> buscarConteudosPorTipo(Authentication authentication, ResenhaModel.TipoConteudo tipoConteudo) {
       UsuarioModel usuario = usuarioRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return resenhaRepository.findAllByUsuarioIdAndTipoConteudo(usuario.getId(), tipoConteudo);
    }



}
