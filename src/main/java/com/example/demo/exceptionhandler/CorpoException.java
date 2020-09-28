package com.example.demo.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorpoException {

    private Integer status;
    private LocalDateTime dataHora;
    private String mensagem;
    private List<Campo> campo;

    @Data
    @AllArgsConstructor
    public static class Campo {
        private String nome;
        private String mensagem;
    }

}
