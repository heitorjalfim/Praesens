package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.service.PrevisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/previsao")
public class PrevisaoController {

    @Autowired
    private PrevisaoService previsaoService;

    @PostMapping("/analisar")
    public ResponseEntity<ScoreOutputDTO> analisar(@RequestBody AgendamentoInputDTO dados) {
        ScoreOutputDTO resultado = previsaoService.calcularRisco(dados);
        return ResponseEntity.ok(resultado);
    }
}