package br.com.uallace.controle.repositories;

import br.com.uallace.controle.domain.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByRg(String rg);
    Optional<Paciente> findByCns(String cns);

}
