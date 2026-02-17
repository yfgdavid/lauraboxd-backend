package com.example.lauraboxd.controller;


import com.example.lauraboxd.dto.ResenhaRequestDTO;
import com.example.lauraboxd.dto.ResenhaResponseDTO;
import com.example.lauraboxd.model.ResenhaModel;
import com.example.lauraboxd.service.ResenhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resenha")
public class ResenhaController {

    @Autowired
    private ResenhaService resenhaService;


    @PostMapping
    public ResponseEntity<ResenhaResponseDTO> criarConteudo(
            @Valid @RequestBody ResenhaRequestDTO requestDTO,
            Authentication authentication) {

        ResenhaModel novoConteudo = resenhaService.criarConteudo(requestDTO, authentication);
        return ResponseEntity.status(201).body(ResenhaResponseDTO.fromModel(novoConteudo));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConteudo(Authentication authentication, @PathVariable Long id) {
        resenhaService.deletarConteudosPorId(id, authentication);
        return (ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenhaResponseDTO> getConteudoById(Authentication authentication, @PathVariable Long id) {
        return resenhaService.listarConteudosPorid(id, authentication)
                .map(conteudos -> {
                    ResenhaResponseDTO dto = ResenhaResponseDTO.fromModel(conteudos);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @GetMapping
    public ResponseEntity<List<ResenhaResponseDTO>> getAllConteudos(Authentication authentication) {
        List<ResenhaResponseDTO> resenhas = resenhaService.listarConteudos(authentication)
                .stream()
                .map(ResenhaResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resenhas);
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<ResenhaResponseDTO>> getConteudosTipo(Authentication authentication, @RequestParam ResenhaModel.TipoConteudo tipoConteudo) {
        List<ResenhaResponseDTO> conteudos = resenhaService.buscarConteudosPorTipo(authentication, tipoConteudo)
                .stream()
                .map(ResenhaResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(conteudos);
    }


}


