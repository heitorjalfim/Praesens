package br.com.meets.cesar.praesens.config;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class ModelConfig {

    @Bean
    public OrtEnvironment ortEnvironment() {
        return OrtEnvironment.getEnvironment();
    }

    @Bean
    public OrtSession ortSession(OrtEnvironment env) throws IOException, OrtException {
        ClassPathResource resource = new ClassPathResource("modelo_clinica.onnx");
        byte[] modelBytes = resource.getInputStream().readAllBytes();
        System.out.println("Config: Carregando modelo ONNX com sucesso.");
        return env.createSession(modelBytes);
    }
}