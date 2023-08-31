package br.com.uallace.controle.services;


import br.com.uallace.controle.domain.dtos.VisitaDTO;
import br.com.uallace.controle.domain.entities.Internacao;
import br.com.uallace.controle.domain.entities.Paciente;
import br.com.uallace.controle.domain.entities.Visita;
import br.com.uallace.controle.domain.enums.Parentesco;
import br.com.uallace.controle.exception.DataIntegrationViolationException;
import br.com.uallace.controle.exception.ObjectNotFound;
import br.com.uallace.controle.repositories.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository repository;
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private InternacaoService internacaoService;


    public List<Visita> findAll(){
        return repository.findAll();
    }

    public Visita findById(Long id){
        Optional<Visita> visita = repository.findById(id);
        return visita.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado! ID: " + id));
    }


    public Visita create(VisitaDTO dto){
        validaPorRg(dto);
        return repository.save(newVisita(dto));
    }

    public Visita update(Long id,VisitaDTO dto){

        dto.setId(id);
        Visita visita =  findById(id);
        validaPorRg(dto);
        visita = newVisita(dto);
        return repository.save(visita);

    }


    private Visita newVisita(VisitaDTO dto){

        Paciente paciente = pacienteService.findById(dto.getPaciente());
        Internacao internacao = internacaoService.findById(dto.getInternacao());

        if(internacao.getData_hora_alta() != null){
            throw new DataIntegrationViolationException("O Paciente relacionado a essa Internacão recebeu Alta!");
        }

        Visita visita = new Visita();

        if(dto.getId() != null){
            visita.setId(dto.getId());
        }

        visita.setNome(dto.getNome());
        visita.setRg(dto.getRg());
        visita.setParentesco(Parentesco.valueOf(dto.getParentesco().getCodigo()));
        visita.setPaciente(paciente);
        visita.setInternacao(internacao);

        return visita;
    }

    private void validaPorRg(VisitaDTO dto){

        Optional<Visita> visita = repository.findByRg(dto.getRg());
        if(visita.isPresent() && visita.get().getId() != dto.getId()){
            throw new DataIntegrationViolationException("RG Já esta cadastrado no sistema!");
        }

    }

    public Visita pacth(Long id, VisitaDTO dto){

        dto.setId(id);
        Visita visita =  findById(id);
        visita.setData_saida(LocalDateTime.now());
        return repository.save(visita);
    }

}
