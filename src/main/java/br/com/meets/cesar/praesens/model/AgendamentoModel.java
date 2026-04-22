package br.com.meets.cesar.praesens.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Agendamento;

    @ManyToOne
    @Setter private PacienteModel paciente;

    @Setter private int LeadTime;
    @Setter private String Tipo_Procedimento;
    @Setter private double Valor_Procedimento;
    @Setter private double Probabilidade_Falta;
}