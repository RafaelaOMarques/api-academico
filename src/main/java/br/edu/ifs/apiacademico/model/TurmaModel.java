package br.edu.ifs.apiacademico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="turma")
public class TurmaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "turma", nullable = false, unique = true)
    private int turmaId;

    @Column(name = "data_inicio", nullable = false)
    private Date dataInicio;

    @Column(name = "data_fim", nullable = false)
    private Date dataFim;

//    @OneToOne
//    @JoinColumn(name = "id_professor", nullable = false)
//    private ProfessorModel professor;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false)
    @JsonBackReference
    private DisciplinaModel disciplina;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AlunoModel> alunos;

//    @OneToMany
//    @JoinColumn(name = "id_aluno", nullable = false)
//    @JsonBackReference
//    private List<AlunoModel> alunos;

//    @ManyToOne
//    @JoinColumn(name = "professor_id")
//    private ProfessorModel professor;

}
