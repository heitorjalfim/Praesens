package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.dto.PrevisaoRequestDTO;
import br.com.meets.cesar.praesens.dto.PrevisaoResponseDTO;
import br.com.meets.cesar.praesens.service.PrevisaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/previsoes")
public class PrevisaoController {

    private final PrevisaoService previsaoService;

    @PostMapping
    public ResponseEntity<PrevisaoResponseDTO> obterPrevisao(@RequestBody @Valid PrevisaoRequestDTO request) {
        PrevisaoResponseDTO resultado = previsaoService.calcularPrevisao(request);
        return ResponseEntity.ok(resultado);
    }
}