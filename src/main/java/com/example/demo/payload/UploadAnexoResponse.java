package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadAnexoResponse {

    private String nome;
    private String downloadUri;
    private String tipo;
    private long size;
}
