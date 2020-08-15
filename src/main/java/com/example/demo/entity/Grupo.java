package com.example.demo.entity;

import java.util.List;

public class Grupo {
    
    private Long id;
    private String nome;
    private String descricao;
    private List<Usuario> administradores;
    private String privacidade;

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

    public List<Usuario> getAdministradores() {
        return this.administradores;
    }

    public void setAdministradores(List<Usuario> administradores) {
        this.administradores = administradores;
    }

    public String getPrivacidade() {
        return this.privacidade;
    }

    public void setPrivacidade(String privacidade) {
        this.privacidade = privacidade;
    }

}