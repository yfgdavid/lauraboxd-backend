package com.example.lauraboxd.dto;

import com.example.lauraboxd.model.ResenhaModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor

public class ResenhaRequestDTO {


        @NotNull(message = "Tipo é obrigatório")
        private ResenhaModel.TipoConteudo tipoConteudo;

        @NotNull(message = "A nota é obrigatória")
        private BigDecimal nota;

        private String comentario;

        private Long tmdbId;

        private Long serieTmdbId;

        private Integer temporadaNumero;
    }


