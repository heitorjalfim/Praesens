package br.com.meets.cesar.praesens.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class TransitoService {

    public float FatorTransito(){
        return 0.2f; 
    }
}