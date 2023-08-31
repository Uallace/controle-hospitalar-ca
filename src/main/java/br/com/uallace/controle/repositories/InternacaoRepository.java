package br.com.uallace.controle.repositories;

import br.com.uallace.controle.domain.entities.Internacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternacaoRepository extends JpaRepository<Internacao, Long> {
}
