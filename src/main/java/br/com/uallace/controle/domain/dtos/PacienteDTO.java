package br.com.uallace.controle.domain.dtos;

import br.com.uallace.controle.domain.entities.Paciente;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

public class PacienteDTO {

    private Long id;

    @NotNull(message = "O campo NOME é requerido!")
    private String nome;

    @NotNull(message = "O campo RG é requerido!")
    private String rg;

    @CPF
    @NotNull(message = "O campo CPF é requerido!")
    private String cpf;

    @NotNull(message = "O campo CNS é requerido!")
    private String cns;

    @NotNull(message = "O campo TELEFONE é requerido!")
    private String telefone;

    public PacienteDTO(){

    }

    public PacienteDTO(Paciente paciente){
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.rg = paciente.getRg();
        this.cpf = paciente.getCpf();
        this.cns = paciente.getCns();
        this.telefone = paciente.getTelefone();
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

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacienteDTO that = (PacienteDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(nome, that.nome)) return false;
        if (!Objects.equals(rg, that.rg)) return false;
        if (!Objects.equals(cpf, that.cpf)) return false;
        if (!Objects.equals(cns, that.cns)) return false;
        return Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (rg != null ? rg.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (cns != null ? cns.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        return result;
    }
}
