package br.com.meets.cesar.praesens.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class ClimaService {

    public float FatorClima() {
        return 0.2f; 
    }
}