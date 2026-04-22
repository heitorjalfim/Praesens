package br.com.meets.cesar.praesens.repository;

import br.com.meets.cesar.praesens.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
    Optional<PacienteModel> findByCpf(String cpf);
}