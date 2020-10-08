package com.example.demo.repository;

import com.example.demo.entity.Grupo;
import com.example.demo.entity.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    boolean existsByMembros(Usuario usuario);
    List<Grupo> findByDono(Usuario dono);

    //@Query(value = "SELECT g FROM Grupo g LEFT JOIN g.membros gm WHERE gm.membro_id = ?1")
    List<Grupo> findByMembros(Usuario usuario);

    @Query(value = "select membro_id from grupo_membros where grupo_id = :grupo", nativeQuery = true)
    List<Long> findMembrosByGrupo(Grupo grupo);

    @Query(value = "select admin_id from grupo_admins where grupo_id = :grupo", nativeQuery = true)
    List<Long> findAdmsByGrupo(Grupo grupo);

    Grupo findByNome(String nome);

}