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


    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = true)
    @JsonBackReference
    private ProfessorModel professor;


    @OneToMany(mappedBy = "disciplina")
    private List<TurmaModel> turmas;
}
