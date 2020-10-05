package com.example.demo.repository;

import com.example.demo.entity.Mensagem;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

    @Query(value = "select * from mensagem inner join envio on destinatario = ?1 group by assunto", nativeQuery = true)
    List<Mensagem> findAllByDestinatario(Long id);

    @Query(value = "select * from mensagem inner join envio on remetente = ?1 group by assunto", nativeQuery = true)
    List<Mensagem> findAllByRemetente(Long id);
}