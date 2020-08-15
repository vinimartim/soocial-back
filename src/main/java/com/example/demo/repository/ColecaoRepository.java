package com.example.demo.repository;

import com.example.demo.entity.Colecao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColecaoRepository extends JpaRepository<Colecao, Long>{
    
}