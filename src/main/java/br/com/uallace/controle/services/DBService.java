package br.com.uallace.controle.services;

import br.com.uallace.controle.domain.entities.Internacao;
import br.com.uallace.controle.domain.entities.Paciente;
import br.com.uallace.controle.domain.entities.Visita;
import br.com.uallace.controle.domain.enums.Parentesco;
import br.com.uallace.controle.domain.enums.Setor;
import br.com.uallace.controle.repositories.InternacaoRepository;
import br.com.uallace.controle.repositories.PacienteRepository;
import br.com.uallace.controle.repositories.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;


@Service
public class DBService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private InternacaoRepository internacaoRepository;

    @Autowired
    private VisitaRepository visitaRepository;

    public void instanciaDB() {

            Paciente p1 = new Paciente(null, "Uallace", "45.760.772-0", "550.482.150-95","010203040506871", "4221-7347");
            Paciente p2 = new Paciente(null, "Ana", "45.050.652-0", "903.347.070-56","010203040506872", "4221-7555");
            Paciente p3 = new Paciente(null, "Mario", "46.070.672-0", "162.720.120-39","010203040506873", "99456-8976");
            Paciente p4 = new Paciente(null, "Jose", "50.050.652-0", "778.556.170-27","010203040506874", "98765-1234");

            Internacao int1 = new Internacao(null,"102", Setor.UTI, p1);
            Internacao int2 = new Internacao(null,"103", Setor.ENFERMARIA, p2);
            Internacao int3 = new Internacao(null,"104", Setor.UTI, p3);
            Internacao int4 = new Internacao(null,"102", Setor.EMERGENCIA, p4);


            Visita v1 = new Visita(null, "Joao", "78.980.786-0", Parentesco.PAI, p2, int1);
            Visita v2 = new Visita(null, "Maria", "23.787.787-90",Parentesco.MAE, p1, int2);
            Visita v3 = new Visita(null, "Arnaldo", "56.786.879-90",Parentesco.IRMAO, p3, int3);
            Visita v4 = new Visita(null, "Joana", "45.675.897-0",Parentesco.AVÓ, p4, int4);


            pacienteRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
            internacaoRepository.saveAll(Arrays.asList(int1,int2,int3,int4));
            visitaRepository.saveAll(Arrays.asList(v1,v2,v3,v4));
    }

}
