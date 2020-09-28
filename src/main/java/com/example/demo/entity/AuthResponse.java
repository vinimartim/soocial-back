package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private final String message;
    private final String token;
    private final Usuario usuario;

}
