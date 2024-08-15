package br.edu.ifs.apiacademico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="disciplina")
public class DisciplinaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "disciplina", nullable = false, unique = true)
    private int disciplinaId;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "numero_creditos", nullable = false)
    private int numeroCreditos;

//    @OneToMany(mappedBy = "disciplina")
//    @JsonManagedReference
//    private List<TurmaModel> turmas;


    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    @JsonBackReference
    private ProfessorModel professor;

//    @ManyToMany
//    @JoinTable(
//    name = "disciplina_aluno",
//    joinColumns = @JoinColumn(name = "id_disciplina"),
//    inverseJoinColumns = @JoinColumn(name = "id_aluno")
//    )
//    private List<AlunoModel> alunos;

    @OneToMany(mappedBy = "disciplina")
    private List<TurmaModel> turmas;
}
