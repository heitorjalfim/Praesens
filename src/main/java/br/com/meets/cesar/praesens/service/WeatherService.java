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

    // Inicialização manual para evitar erros de Bean no contexto do Spring
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestClient restClient = RestClient.create();

    public float obterFatorClima(String cidade) {
        try {
            // 1. Faz a chamada HTTP recebendo o JSON como String pura
            String respostaJson = restClient.get()
                .uri(apiUrl + "?q=" + cidade + "&appid=" + apiKey + "&lang=pt_br&units=metric")
                .retrieve()
                .body(String.class);

            // 2. Converte a String para a árvore de nós do Jackson
            JsonNode resposta = objectMapper.readTree(respostaJson);

            // Print de validação para você acompanhar o JSON no terminal
            System.out.println("RETORNO DA API OPENWEATHER -> " + resposta);

            if (resposta != null && resposta.has("weather")) {
                // 3. Captura o ID meteorológico do Weather do OpenWeather
                int idClima = resposta.get("weather").get(0).get("id").asInt();
                System.out.println("ID do Clima capturado: " + idClima);
                
                return mapearIdClimaParaFator(idClima);
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar OpenWeather: " + e.getMessage());
        }
        return 0.5f; // Fator neutro caso ocorra alguma falha na API ou timeout
    }

    /**
     * Mapeia os códigos oficiais do OpenWeather para um peso entre 0.0 e 1.0 para a IA.
     * IDs oficiais: https://openweathermap.org/weather-conditions
     */
    private float mapearIdClimaParaFator(int id) {
        if (id >= 200 && id < 600) {
            return 0.9f; // Tempestade, Chuvisco ou Chuva forte -> Alto Risco
        } else if (id >= 600 && id < 700) {
            return 0.7f; // Neve / Condições extremas
        } else if (id >= 700 && id < 800) {
            return 0.5f; // Névoa / Nevoeiro
        } else if (id == 803 || id == 804) {
            return 0.5f; // ID 803/804 -> Nublado/Tempo fechado (Puxa o risco um pouco mais para cima!)
        } else {
            return 0.1f; // ID 800, 801, 802 -> Céu limpo ou poucas nuvens -> Seguro
        }
    }
}