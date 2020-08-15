package com.example.demo.entity;

import java.time.LocalDateTime;

public class Usuario {
    
    private Long id;
    private String nome;
    private String sobrenome;
    private String username;
    private String senha;
    private String email;
    private String dataNascimento;
    private LocalDateTime ultimoLogin;
    private LocalDateTime ultimoLogout;
    private boolean statusCadastro;
    private String genero;
    private String telefone;

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

    public String getSobrenome() {
        return this.sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getUltimoLogin() {
        return this.ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public LocalDateTime getUltimoLogout() {
        return this.ultimoLogout;
    }

    public void setUltimoLogout(LocalDateTime ultimoLogout) {
        this.ultimoLogout = ultimoLogout;
    }

    public boolean isStatusCadastro() {
        return this.statusCadastro;
    }

    public void setStatusCadastro(boolean statusCadastro) {
        this.statusCadastro = statusCadastro;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}