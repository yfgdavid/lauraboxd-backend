package com.example.lauraboxd.repository;

import com.example.lauraboxd.model.ResenhaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ResenhaRepository extends JpaRepository<ResenhaModel, Long> {

    List<ResenhaModel> findByUsuarioId(Long usuarioId);

    List<ResenhaModel> findAllByUsuarioIdAndTipoConteudo(Long usuarioId, ResenhaModel.TipoConteudo tipoConteudo);

    List<ResenhaModel> findAllByUsuarioId(Long usuarioId);


    Optional<ResenhaModel> findByIdResenhaAndUsuarioId(Long idResenha, Long usuarioId);


}

