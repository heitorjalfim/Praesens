package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.model.PacienteModel;
import br.com.meets.cesar.praesens.repository.AgendamentoRepository;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public AgendamentoModel salvar(AgendamentoModel agendamento) {
        // No seu Model o paciente é um objeto. Vamos garantir que ele existe no banco.
        PacienteModel paciente = agendamento.getPaciente();
        
        // Se o paciente for novo (ID nulo), salvamos primeiro. 
        // Se já existir, o JPA trata de associar.
        if (paciente.getID_Paciente() == null) {
            paciente = pacienteRepository.save(paciente);
        }

        agendamento.setPaciente(paciente);
        return agendamentoRepository.save(agendamento);
    }

    public void registrarFalta(Long idAgendamento) {
        agendamentoRepository.findById(idAgendamento).ifPresent(agendamento -> {
            PacienteModel paciente = agendamento.getPaciente();
            
            int faltasAtuais = paciente.getHistorico_NoShow();
            paciente.setHistorico_NoShow(faltasAtuais + 1);
            
            int honraAtual = paciente.getScore_Honra();
            if (honraAtual > 0) paciente.setScore_Honra(honraAtual - 10);

            pacienteRepository.save(paciente);
        });
    }

    public List<AgendamentoModel> listarTodos() {
        return agendamentoRepository.findAll();
    }
}