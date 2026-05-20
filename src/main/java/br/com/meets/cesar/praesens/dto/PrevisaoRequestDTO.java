package br.com.meets.cesar.praesens.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrevisaoRequestDTO(
    @NotBlank(message = "O tipo do procedimento é obrigatório.")
    String tipoProcedimento,

    @NotNull(message = "A hora do agendamento é obrigatória.")
    @Min(value = 0, message = "A hora não pode ser menor que 0.")
    Integer horaAgendamento,

    @NotNull(message = "O valor do procedimento é obrigatório.")
    @Min(value = 0, message = "O valor do procedimento não pode ser negativo.")
    Double valorProcedimento,

    @NotNull(message = "O total de agendamentos do paciente é obrigatório.")
    @Min(value = 0, message = "O total de agendamentos não pode ser negativo.")
    Integer totalAgendamentos,

    @NotNull(message = "O histórico de faltas (no-show) é obrigatório.")
    @Min(value = 0, message = "O total de faltas não pode ser negativo.")
    Integer totalFaltas
) {}