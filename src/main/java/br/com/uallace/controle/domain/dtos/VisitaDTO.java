package br.com.uallace.controle.domain.dtos;

import br.com.uallace.controle.domain.entities.Visita;
import br.com.uallace.controle.domain.enums.Parentesco;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class VisitaDTO {

    private Long id;

    @NotNull(message = "O campo NOME é requerido!")
    private String nome;

    @NotNull(message = "O campo RG é requerido!")
    private String rg;

    @NotNull(message = "O campo PARENTESCO é requerido!")
    private Integer parentesco;

    @NotNull(message = "O campo PACIENTE é requerido!")
    private Long paciente;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_entrada;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_saida;

    @NotNull(message = "O campo INTERNAÇÃO é requerido!")
    private Long internacao;

    public VisitaDTO(){

    }

    public VisitaDTO(Visita visita){
        this.id = visita.getId();;
        this.nome = visita.getNome();
        this.rg = visita.getRg();
        this.parentesco = visita.getParentesco().getCodigo();
        this.paciente = visita.getPaciente().getId();
        this.data_entrada = visita.getData_entrada();
        this.data_saida = visita.getData_saida();
        this.internacao = visita.getInternacao().getId();
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

    public Parentesco getParentesco() {
        return Parentesco.valueOf(parentesco);
    }

    public void setParentesco(Parentesco parentesco) {

        this.parentesco = parentesco.getCodigo();
    }

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDateTime data_entrada) {
        this.data_entrada = data_entrada;
    }

    public LocalDateTime getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDateTime data_saida) {
        this.data_saida = data_saida;
    }

    public Long getInternacao() {
        return internacao;
    }

    public void setInternacao(Long internacao) {
        this.internacao = internacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitaDTO visitaDTO = (VisitaDTO) o;

        if (!id.equals(visitaDTO.id)) return false;
        if (!Objects.equals(nome, visitaDTO.nome)) return false;
        if (!Objects.equals(rg, visitaDTO.rg)) return false;
        if (!Objects.equals(parentesco, visitaDTO.parentesco)) return false;
        if (!Objects.equals(paciente, visitaDTO.paciente)) return false;
        if (!Objects.equals(data_entrada, visitaDTO.data_entrada))
            return false;
        return Objects.equals(data_saida, visitaDTO.data_saida);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (rg != null ? rg.hashCode() : 0);
        result = 31 * result + (parentesco != null ? parentesco.hashCode() : 0);
        result = 31 * result + (paciente != null ? paciente.hashCode() : 0);
        result = 31 * result + (data_entrada != null ? data_entrada.hashCode() : 0);
        result = 31 * result + (data_saida != null ? data_saida.hashCode() : 0);
        return result;
    }
}
