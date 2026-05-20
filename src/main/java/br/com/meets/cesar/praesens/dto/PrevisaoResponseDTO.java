package br.com.meets.cesar.praesens.dto;

public record PrevisaoResponseDTO(
    double probabilidadeNoShow,
    String riscoDescricao,
    String recomendacao
) {}