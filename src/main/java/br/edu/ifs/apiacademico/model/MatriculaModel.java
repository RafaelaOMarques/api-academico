package br.edu.ifs.apiacademico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="matricula")
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    @JsonBackReference
    private AlunoModel aluno;

    @Column(name = "matricula", nullable = false, unique = true)
    private int matricula;

    @Column(name = "matricula_ativa", nullable = false)
    private boolean matriculaAtiva;

}
