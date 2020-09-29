package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
    @Query(value =
            "SELECT * FROM usuario u WHERE u.id IN (SELECT * FROM seguidores s WHERE s.usuario_id = :usuario_id)",
            nativeQuery = true
    )
    List<Usuario> findSeguidoresById(@Param("usuario_id") Long usuario_id);
}