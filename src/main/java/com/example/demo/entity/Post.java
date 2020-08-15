package com.example.demo.entity;

public class Post {
    
    private Long id;
    private String conteudo;
    private boolean edicao;
    private boolean spam;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public boolean isEdicao() {
        return this.edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public boolean isSpam() {
        return this.spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }

}