package br.com.uallace.controle.controllers;

import br.com.uallace.controle.domain.dtos.InternacaoDTO;
import br.com.uallace.controle.domain.dtos.VisitaDTO;
import br.com.uallace.controle.domain.entities.Internacao;
import br.com.uallace.controle.domain.entities.Visita;
import br.com.uallace.controle.services.VisitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/visitas")
public class VisitaController {

    @Autowired
    private VisitaService service;

    @GetMapping
    public ResponseEntity<List<VisitaDTO>> getAll(){
        List<VisitaDTO> listDTO = service.findAll().stream().map(v -> new VisitaDTO(v)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitaDTO> getById(@PathVariable("id") Long id){
        Visita visita = service.findById(id);
        return ResponseEntity.ok().body(new VisitaDTO(visita));
    }

    @PostMapping
    public ResponseEntity<VisitaDTO> create(@Valid @RequestBody VisitaDTO dto){
        Visita visita = service.create(dto);
        VisitaDTO visitaDTO = new VisitaDTO(visita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(visitaDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitaDTO> update(@PathVariable("id") Long id, @Valid @RequestBody VisitaDTO dto){
        Visita visita = service.update(id,dto);
        return ResponseEntity.ok().body(new VisitaDTO(visita));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VisitaDTO> patch(@PathVariable("id") Long id, VisitaDTO dto){
        Visita visita =  service.pacth(id,dto);
        return ResponseEntity.ok().body(new VisitaDTO(visita));
    }

}
