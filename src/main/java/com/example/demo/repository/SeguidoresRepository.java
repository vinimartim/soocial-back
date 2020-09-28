package com.example.demo.repository;

import com.example.demo.entity.Seguidores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeguidoresRepository extends JpaRepository<Seguidores, Long> {

    @Query(value = "SELECT * FROM seguidores s WHERE s.quem_segue = :id", nativeQuery = true)
    List<Seguidores> findAllByQuemSegue(@Param("id") Long id);
}
