package br.com.uallace.controle.domain.entities;

import br.com.uallace.controle.domain.enums.Parentesco;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Visita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String rg;

    private Integer parentesco;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data_hora_entrada = LocalDateTime.now();

    private LocalDateTime data_hora_saida;

    @ManyToOne
    @JoinColumn(name = "internacao_id")
    private Internacao internacao;


    public Visita(){

    }

    public Visita(Long id, String nome, String rg,Parentesco parentesco, Paciente paciente, Internacao internacao) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.paciente = paciente;
        this.internacao = internacao;
        setParentesco(parentesco);
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getData_entrada() {
        return data_hora_entrada;
    }

    public void setData_entrada(LocalDateTime data_entrada) {
        this.data_hora_entrada = data_entrada;
    }

    public LocalDateTime getData_saida() {
        return data_hora_saida;
    }

    public void setData_saida(LocalDateTime data_saida) {
        this.data_hora_saida = data_saida;
    }

    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visita visita = (Visita) o;

        if (!Objects.equals(id, visita.id)) return false;
        if (!Objects.equals(nome, visita.nome)) return false;
        if (!Objects.equals(rg, visita.rg)) return false;
        if (!Objects.equals(paciente, visita.paciente)) return false;
        if (!Objects.equals(data_hora_entrada, visita.data_hora_entrada))
            return false;
        return Objects.equals(data_hora_saida, visita.data_hora_saida);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (rg != null ? rg.hashCode() : 0);
        result = 31 * result + (paciente != null ? paciente.hashCode() : 0);
        result = 31 * result + (data_hora_entrada != null ? data_hora_entrada.hashCode() : 0);
        result = 31 * result + (data_hora_saida != null ? data_hora_saida.hashCode() : 0);
        return result;
    }
}
