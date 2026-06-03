package br.com.meets.cesar.praesens.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestClient restClient = RestClient.create();

    public float obterFatorClima(String cidade) {
        try {
            String respostaJson = restClient.get()
                .uri(apiUrl + "?q=" + cidade + "&appid=" + apiKey + "&lang=pt_br&units=metric")
                .retrieve()
                .body(String.class);

            JsonNode resposta = objectMapper.readTree(respostaJson);

            System.out.println("RETORNO DA API OPENWEATHER -> " + resposta);

            if (resposta != null && resposta.has("weather")) {
                int idClima = resposta.get("weather").get(0).get("id").asInt();
                System.out.println("ID do Clima capturado: " + idClima);
                
                return mapearIdClimaParaFator(idClima);
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar OpenWeather: " + e.getMessage());
        }
        return 0.5f; 
    }

    private float mapearIdClimaParaFator(int id) {
        if (id >= 200 && id < 600) {
            return 0.9f; 
        } else if (id >= 600 && id < 700) {
            return 0.7f; 
        } else if (id >= 700 && id < 800) {
            return 0.5f; 
        } else if (id == 803 || id == 804) {
            return 0.5f;
        } else {
            return 0.1f;
        }
    }
}