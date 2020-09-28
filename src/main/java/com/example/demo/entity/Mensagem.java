package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mensagem")
public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "assunto")
    private String assunto;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "visualizada")
    private boolean visualizada;

    @Column(name = "spam")
    private boolean spam;

    @Column(name = "edicao")
    private boolean edicao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anexo_id")
    private Anexo anexo;
}