package com.example.demo.dto.assember;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;

import javax.persistence.EntityManager;

public class MensagemAssember {

    /**
     * Função que transforma um objeto DTO em um objeto
     * @param dto o objeto dto
     * @return mensagem
     */
    public static Mensagem dtoToEntityModel(MensagemDTO dto) {
        Mensagem mensagem = new Mensagem();
        mensagem.setAssunto(dto.getAssunto());
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setEdicao(dto.isEdicao());
        mensagem.setSpam(dto.isSpam());
        mensagem.setEnvio(dto.getEnvio());
        mensagem.setVisualizada(dto.isVisualizada());
        mensagem.setAnexo(dto.getAnexo());

        return mensagem;
    }
}
