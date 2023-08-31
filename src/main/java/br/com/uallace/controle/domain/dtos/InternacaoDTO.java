package br.com.uallace.controle.domain.dtos;

import br.com.uallace.controle.domain.entities.Internacao;
import br.com.uallace.controle.domain.enums.Setor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class InternacaoDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_hora_internacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_hora_alta;

    @NotNull(message = "O campo QUARTO_LEITO é requerido!")
    private String quarto_leito;

    @NotNull(message = "O campo SETOR é requerido!")
    private Integer setor;

    @NotNull(message = "O campo PACIENTE é requerido!")
    private Long paciente;

    private String nomePaciente;

    public InternacaoDTO(){

    }

    public InternacaoDTO(Internacao internacao){
        this.id = internacao.getId();
        this.data_hora_internacao = internacao.getData_hora_internacao();
        this.data_hora_alta = internacao.getData_hora_alta();
        this.quarto_leito = internacao.getQuarto_leito();
        this.setor = internacao.getSetor().getCodigo();
        this.paciente = internacao.getPaciente().getId();
        this.nomePaciente = internacao.getPaciente().getNome();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_hora_internacao() {
        return data_hora_internacao;
    }

    public void setData_hora_internacao(LocalDateTime data_hora_internacao) {
        this.data_hora_internacao = data_hora_internacao;
    }

    public LocalDateTime getData_hora_alta() {
        return data_hora_alta;
    }

    public void setData_hora_alta(LocalDateTime data_hora_alta) {
        this.data_hora_alta = data_hora_alta;
    }

    public String getQuarto_leito() {
        return quarto_leito;
    }

    public void setQuarto_leito(String quarto_leito) {
        this.quarto_leito = quarto_leito;
    }

    public Setor getSetor() {
        return Setor.valueOf(setor);
    }

    public void setSetor(Setor setor) {
        this.setor = setor.getCodigo();
    }

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InternacaoDTO that = (InternacaoDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(data_hora_internacao, that.data_hora_internacao))
            return false;
        if (!Objects.equals(data_hora_alta, that.data_hora_alta)) return false;
        if (!Objects.equals(quarto_leito, that.quarto_leito)) return false;
        if (!Objects.equals(setor, that.setor)) return false;
        return Objects.equals(paciente, that.paciente);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (data_hora_internacao != null ? data_hora_internacao.hashCode() : 0);
        result = 31 * result + (data_hora_alta != null ? data_hora_alta.hashCode() : 0);
        result = 31 * result + (quarto_leito != null ? quarto_leito.hashCode() : 0);
        result = 31 * result + (setor != null ? setor.hashCode() : 0);
        result = 31 * result + (paciente != null ? paciente.hashCode() : 0);
        return result;
    }
}
