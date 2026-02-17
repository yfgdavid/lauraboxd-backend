package com.example.lauraboxd.dto;

import com.example.lauraboxd.model.ResenhaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter @NoArgsConstructor
@AllArgsConstructor

public class ResenhaResponseDTO {
    private Long idResenha;
    private Long tmdbId;
    private BigDecimal nota;
    private String comentario;
    private ResenhaModel.TipoConteudo tipoConteudo;
    private Long idSerieTmdb;
    private Integer TemporadaNumero;

    public static ResenhaResponseDTO fromModel (ResenhaModel resenhaModel) {
        return new ResenhaResponseDTO(
                resenhaModel.getIdResenha(),
                resenhaModel.getTmdbId(),
                resenhaModel.getNota(),
                resenhaModel.getComentario(),
                resenhaModel.getTipoConteudo(),
                resenhaModel.getSerieTmdbId(),
                resenhaModel.getTemporadaNumero()
        );
    }
}
