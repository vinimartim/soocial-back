package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "envio")
public class Envio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime dataHoraEnvio;

    @Column
    private LocalDateTime dataHoraEdicao;

    @JsonIgnore
    @OneToOne(mappedBy = "envio")
    private Mensagem mensagem;

    @ManyToOne
    @JoinColumn(name = "remetente")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario")
    private Usuario destinatario;

    @PrePersist
    public void setDataHoraEnvio() {
        this.dataHoraEnvio = LocalDateTime.now();
    }

    @PreUpdate
    public void setDataHoraEdicao() {
        this.dataHoraEdicao = LocalDateTime.now();
    }
}
