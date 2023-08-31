package br.com.uallace.controle.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String rg;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String cns;

    private String telefone;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Internacao> internacoes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Visita> visita = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(Long id, String nome, String rg, String cpf,String cns, String telefone) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.cns = cns;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    @OneToMany(mappedBy = "paciente")
    public List<Internacao> getInternacoes() {
        return internacoes;
    }

    public void setInternacoes(List<Internacao> internacoes) {

        this.internacoes = internacoes;
    }

    public List<Visita> getVisita() {
        return visita;
    }

    public void setVisita(List<Visita> visita) {
        this.visita = visita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paciente paciente = (Paciente) o;

        if (!Objects.equals(id, paciente.id)) return false;
        if (!Objects.equals(nome, paciente.nome)) return false;
        if (!Objects.equals(rg, paciente.rg)) return false;
        if (!Objects.equals(cpf, paciente.cpf)) return false;
        if (!Objects.equals(telefone, paciente.telefone)) return false;
        if (!Objects.equals(internacoes, paciente.internacoes))
            return false;
        return Objects.equals(visita, paciente.visita);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (rg != null ? rg.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (internacoes != null ? internacoes.hashCode() : 0);
        result = 31 * result + (visita != null ? visita.hashCode() : 0);
        return result;
    }
}
