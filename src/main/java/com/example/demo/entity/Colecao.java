package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Colecao {
    
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private boolean arquivada;
    private String privacidade;
    private List<Usuario> colaboradores;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicio() {
        return this.inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return this.fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public boolean isArquivada() {
        return this.arquivada;
    }

    public void setArquivada(boolean arquivada) {
        this.arquivada = arquivada;
    }

    public String getPrivacidade() {
        return this.privacidade;
    }

    public void setPrivacidade(String privacidade) {
        this.privacidade = privacidade;
    }

    public List<Usuario> getColaboradores() {
        return this.colaboradores;
    }

    public void setColaboradores(List<Usuario> colaboradores) {
        this.colaboradores = colaboradores;
    }

}