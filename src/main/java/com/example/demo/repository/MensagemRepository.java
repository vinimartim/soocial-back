package com.example.demo.repository;

import com.example.demo.entity.Mensagem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
    
}