package br.com.uallace.controle.controllers;

import br.com.uallace.controle.domain.dtos.PacienteDTO;
import br.com.uallace.controle.domain.entities.Paciente;
import br.com.uallace.controle.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAll(){
        List<PacienteDTO> listDTO = service.findAll().stream().map(p -> new PacienteDTO(p)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable("id") Long id){
        Paciente paciente = service.findById(id);
        return ResponseEntity.ok().body(new PacienteDTO(paciente));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO dto){
        Paciente paciente = service.create(dto);
        PacienteDTO pacienteDTO = new PacienteDTO(paciente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pacienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable("id") Long id, @Valid @RequestBody PacienteDTO dto){
        Paciente paciente = service.update(id,dto);
        return ResponseEntity.ok().body(new PacienteDTO(paciente));
    }
}
