package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.dto.PrevisaoRequestDTO;
import br.com.meets.cesar.praesens.dto.PrevisaoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrevisaoService {

    private final ClimaService climaService;
    private final TransitoService transitoService;
    private final IAService iaService;

    public PrevisaoResponseDTO calcularPrevisao(PrevisaoRequestDTO dadosClinica) {
        float clima = climaService.FatorClima();
        float transito = transitoService.FatorTransito();

        double probabilidade = iaService.preverRisco(dadosClinica, clima, transito);

        String riscoDescricao;
        String recomendacao;

        if (probabilidade < 0) {
            riscoDescricao = "Erro";
            recomendacao = "Não foi possível calcular a previsão.";
        } else if (probabilidade > 0.7) {
            riscoDescricao = "Alto Risco";
            recomendacao = "Enviar lembrete reforçado e confirmar presença via ligação.";
        } else if (probabilidade > 0.4) {
            riscoDescricao = "Médio Risco";
            recomendacao = "Enviar mensagem automática de confirmação de presença.";
        } else {
            riscoDescricao = "Baixo Risco";
            recomendacao = "Seguir com fluxo padrão de agendamento.";
        }

        return new PrevisaoResponseDTO(probabilidade, riscoDescricao, recomendacao);
    }
}