package com.example.demo.repository;

import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;
import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    Optional<Envio> findByMensagem(Mensagem mensagem);
    boolean existsByMensagem(Mensagem mensagem);
    List<Envio> findAllByDestinatario(Usuario usuario);
    List<Envio> findAllByRemetente(Usuario usuario);
}
