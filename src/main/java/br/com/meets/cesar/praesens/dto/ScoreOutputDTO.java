package br.com.meets.cesar.praesens.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreOutputDTO {
    private double probabilidadeFalta;
    private String justificativa;
    private String condicaoClimatica;
    private String recomendacao; 
}