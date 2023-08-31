package br.com.uallace.controle.services;

import br.com.uallace.controle.domain.dtos.PacienteDTO;
import br.com.uallace.controle.domain.entities.Paciente;
import br.com.uallace.controle.exception.DataIntegrationViolationException;
import br.com.uallace.controle.exception.ObjectNotFound;
import br.com.uallace.controle.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<Paciente> findAll(){

        return repository.findAll();
    }

    public Paciente findById(Long id){
        Optional<Paciente> paciente = repository.findById(id);
        return paciente.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado! ID: " + id));
    }


    public Paciente create(PacienteDTO dto){
        validaDocs(dto);
        return repository.save(newInternacao(dto));
    }

    public Paciente update(Long id,PacienteDTO dto){
        dto.setId(id);
        Paciente paciente =  findById(id);
        validaDocs(dto);
        paciente = newInternacao(dto);
        return repository.save(paciente);
    }


    private Paciente newInternacao(PacienteDTO dto){

        Paciente paciente = new Paciente();

        if(dto.getId() != null){
            paciente.setId(dto.getId());
        }

        paciente.setNome(dto.getNome());
        paciente.setRg(dto.getRg());
        paciente.setCpf(dto.getCpf());
        paciente.setCns(dto.getCns());
        paciente.setTelefone(dto.getTelefone());

        return paciente;
    }

    private void validaDocs(PacienteDTO dto) {

        Optional<Paciente> paciente = repository.findByCpf(dto.getCpf());

        if(paciente.isPresent() && paciente.get().getId() != dto.getId()){
            throw new DataIntegrationViolationException("CPF Já cadastrado no sistema!");
        }

        paciente = repository.findByRg(dto.getRg());

        if(paciente.isPresent() && paciente.get().getId() != dto.getId()){
            throw new DataIntegrationViolationException("RG Já cadastrado no sistema!");
        }

        paciente = repository.findByCns(dto.getCns());

        if(paciente.isPresent() && paciente.get().getId() != dto.getId()){
            throw new DataIntegrationViolationException("CNS Já cadastrado no sistema!");
        }

    }

}
