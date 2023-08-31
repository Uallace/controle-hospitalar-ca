package br.com.uallace.controle.services;


import br.com.uallace.controle.domain.dtos.InternacaoDTO;
import br.com.uallace.controle.domain.entities.Internacao;
import br.com.uallace.controle.domain.entities.Paciente;
import br.com.uallace.controle.domain.enums.Setor;
import br.com.uallace.controle.exception.DataIntegrationViolationException;
import br.com.uallace.controle.exception.ObjectNotFound;
import br.com.uallace.controle.repositories.InternacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InternacaoService {

    @Autowired
    private InternacaoRepository repository;

    @Autowired
    private PacienteService pacienteService;

    public List<Internacao> findAll(){
        return repository.findAll();
    }

    public Internacao findById(Long id){
        Optional<Internacao> internacao = repository.findById(id);
        return internacao.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado! ID: " + id));
    }


    public Internacao create(InternacaoDTO dto){
          return repository.save(newInternacao(dto));

    }

    public Internacao update(Long id,InternacaoDTO dto){

        dto.setId(id);
        Internacao internacao =  findById(id);
        internacao = newInternacao(dto);
        return repository.save(internacao);

    }


    private Internacao newInternacao(InternacaoDTO dto){
        Paciente paciente = pacienteService.findById(dto.getPaciente());

        for(Internacao i : paciente.getInternacoes()){
            if(i.getData_hora_alta() == null){
                throw new DataIntegrationViolationException("Paciente com internacão em andamento!");
            }
        }

        Internacao internacao = new Internacao();

        if(dto.getId() != null){
            internacao.setId(dto.getId());
        }


        internacao.setQuarto_leito(dto.getQuarto_leito());
        internacao.setData_hora_internacao(LocalDateTime.now());
        internacao.setPaciente(paciente);
        internacao.setSetor(Setor.valueOf(dto.getSetor().getCodigo()));

        return internacao;
    }

    public Internacao pacth(Long id,InternacaoDTO dto){

        dto.setId(id);
        Internacao internacao =  findById(id);
        internacao.setData_hora_alta(LocalDateTime.now());
        return repository.save(internacao);
    }

}
