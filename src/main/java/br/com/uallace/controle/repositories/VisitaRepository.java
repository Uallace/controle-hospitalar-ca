package br.com.uallace.controle.repositories;

import br.com.uallace.controle.domain.entities.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitaRepository extends JpaRepository<Visita, Long> {

    Optional<Visita> findByRg(String rg);

}
