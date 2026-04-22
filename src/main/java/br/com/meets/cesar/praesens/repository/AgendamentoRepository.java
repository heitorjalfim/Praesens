package br.com.meets.cesar.praesens.repository;

import br.com.meets.cesar.praesens.model.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long>{
}
