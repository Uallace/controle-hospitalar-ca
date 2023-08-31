package br.com.uallace.controle.domain.entities;

import br.com.uallace.controle.domain.enums.Setor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Internacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_hora_internacao = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_hora_alta;
    private String quarto_leito;

    private Integer setor;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToMany(mappedBy = "internacao")
    private List<Visita> visitas = new ArrayList<>();

    public Internacao(){

    }

    public Internacao(Long id,String quarto_leito, Setor setor, Paciente paciente) {
        this.id = id;
        this.data_hora_internacao = data_hora_internacao;
        this.data_hora_alta = data_hora_alta;
        this.quarto_leito = quarto_leito;
        this.paciente = paciente;
        setSetor(setor);
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Internacao that = (Internacao) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(data_hora_internacao, that.data_hora_internacao))
            return false;
        if (!Objects.equals(data_hora_internacao, that.data_hora_internacao)) return false;
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
