package br.com.uallace.controle.controllers;

import br.com.uallace.controle.domain.dtos.InternacaoDTO;
import br.com.uallace.controle.domain.entities.Internacao;
import br.com.uallace.controle.services.InternacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {

    @Autowired
    private InternacaoService service;

    @GetMapping
    public ResponseEntity<List<InternacaoDTO>> getAll(){
        List<InternacaoDTO> listDTO = service.findAll().stream().map(i -> new InternacaoDTO(i)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternacaoDTO> getById(@PathVariable("id") Long id){
        Internacao internacao = service.findById(id);
        return ResponseEntity.ok().body(new InternacaoDTO(internacao));
    }

    @PostMapping
    public ResponseEntity<InternacaoDTO> create(@Valid @RequestBody InternacaoDTO dto){
        Internacao internacao = service.create(dto);
        InternacaoDTO internacaoDTO = new InternacaoDTO(internacao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(internacaoDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternacaoDTO> update(@PathVariable("id") Long id, @Valid @RequestBody InternacaoDTO dto){
        Internacao internacao = service.update(id,dto);
        return ResponseEntity.ok().body(new InternacaoDTO(internacao));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InternacaoDTO> patch(@PathVariable("id") Long id, InternacaoDTO dto){
        Internacao internacao =  service.pacth(id,dto);
        return ResponseEntity.ok().body(new InternacaoDTO(internacao));
    }

}
