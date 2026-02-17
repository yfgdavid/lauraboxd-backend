package com.example.lauraboxd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(
        name = "resenha",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "resenha_usuario_tipo",
                        columnNames = {"id_usuario", "tipo", "tmdb_id"}
                ),
                @UniqueConstraint(
                        name = "resenha_temporadas",
                        columnNames = {"id_usuario", "tipo", "serie_tmdb_id", "temporada_numero"}

                )
        }
)

public class ResenhaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResenha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataRegistro;

    @PrePersist
    public void prePersist() {
        this.dataRegistro = LocalDateTime.now();
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoConteudo tipoConteudo;

    @Column(name = "tmdb_id", nullable = true)
    private Long tmdbId;

    @NotNull(message = "A nota é obrigatória.")
    @Column(name = "nota", nullable = false, precision = 3, scale = 2)
    @DecimalMin(value = "1.0", message = "A nota deve ser no mínimo 1.")
    @DecimalMax(value = "5.0", message = "A nota deve ser no máximo 5.")
    private BigDecimal nota;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "serie_tmdb_id", nullable = true)
    private Long serieTmdbId;

    @Column(name = "temporada_numero", nullable = true)
    private Integer temporadaNumero;


    public enum TipoConteudo {
        FILME,
        SERIE,
        TEMPORADA
    }

}

