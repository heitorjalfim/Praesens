package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.model.PacienteModel;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrevisaoService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public ScoreOutputDTO calcularRisco(AgendamentoInputDTO dados) {
        
        PacienteModel paciente = pacienteRepository.findByCpf(dados.getCpfPaciente())
                .orElse(null);

        double scoreFinal = 0.0;

        if (paciente != null) {
            // logica
        
            
            double fatorFalta = paciente.getHistorico_NoShow() * 0.5;
            double fatorHonra = (100 - paciente.getScore_Honra()) * 0.3;
            
            scoreFinal = fatorFalta + fatorHonra;
        }
        
        ScoreOutputDTO output = new ScoreOutputDTO();
        output.setProbabilidadeFalta(Math.min(scoreFinal, 100.0)); 
        
        return output;
    }
}