package com.example.demo.entity;

public class Mensagem {
    
    private Long id;
    private String assunto;
    private String conteudo;
    private boolean visualizada;
    private boolean spam;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return this.assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public boolean isVisualizada() {
        return this.visualizada;
    }

    public void setVisualizada(boolean visualizada) {
        this.visualizada = visualizada;
    }

    public boolean isSpam() {
        return this.spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }

}