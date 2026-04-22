package br.com.meets.cesar.praesens.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AgendamentoInputDTO {
    private String cpfPaciente;   
    private String localidade;    
    private LocalDateTime dataHora;
}