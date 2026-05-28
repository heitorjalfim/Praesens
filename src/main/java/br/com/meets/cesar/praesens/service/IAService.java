package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.dto.PrevisaoRequestDTO;
import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class IAService {
    private final OrtEnvironment env;
    private final OrtSession session;

    public double preverRisco(PrevisaoRequestDTO dto, float fatorClima, float fatorTransito) {
        try {
            float[][] features = prepararDados(dto, fatorClima, fatorTransito);
            
            try (OnnxTensor tensor = OnnxTensor.createTensor(env, features);
                 OrtSession.Result resultado = session.run(Collections.singletonMap("input", tensor))) {

                float[][] outputProbabilidades = (float[][]) resultado.get(1).getValue();
                
                float probabilidadeFalta = outputProbabilidades[0][1];
                
                return (double) probabilidadeFalta;
            }
        } catch (Exception e) {
            System.err.println("IA: Erro durante a execução da inferência do ONNX: " + e.getMessage());
            e.printStackTrace();
            return -1.0;
        }
    }
    
    private float[][] prepararDados(PrevisaoRequestDTO dto, float fatorClima, float fatorTransito) {
        float tipoNum = (dto.tipoProcedimento() != null && 
                         dto.tipoProcedimento().equalsIgnoreCase("Estético")) ? 1.0f : 0.0f;

        return new float[][] {
            {
                tipoNum,
                dto.horaAgendamento().floatValue(),
                dto.valorProcedimento().floatValue(),
                dto.totalAgendamentos().floatValue(),
                dto.totalFaltas().floatValue(),
                fatorClima,
                fatorTransito
            }
        };
    }
}