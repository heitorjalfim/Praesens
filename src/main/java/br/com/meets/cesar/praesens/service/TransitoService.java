package br.com.meets.cesar.praesens.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransitoService {

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Value("${tomtom.api.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestClient restClient = RestClient.create();

    public float obterFatorTransito(double latitude, double longitude) {
        try {
            String ponto = latitude + "," + longitude;

            String respostaJson = restClient.get()
                .uri(apiUrl + "?point=" + ponto + "&key=" + apiKey)
                .retrieve()
                .body(String.class);

            JsonNode resposta = objectMapper.readTree(respostaJson);

            System.out.println("RETORNO DA API TOMTOM TRAFFIC -> " + resposta);

            if (resposta != null && resposta.has("flowSegmentData")) {
                JsonNode flowData = resposta.get("flowSegmentData");
    
                double currentSpeed = flowData.get("currentSpeed").asDouble();
                double freeFlowSpeed = flowData.get("freeFlowSpeed").asDouble();

                if (freeFlowSpeed > 0) {
                    double perdaVelocidade = 1.0 - (currentSpeed / freeFlowSpeed);
                
                    float fatorTransito = (float) Math.max(0.0, Math.min(1.0, perdaVelocidade));
                    System.out.println("Fator Trânsito Calculado (TomTom): " + fatorTransito);
                    
                    return fatorTransito;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar TomTom Traffic: " + e.getMessage());
        }
        return 0.5f;
    }
}