package br.edu.ifs.apiacademico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="matricula")
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idAluno", nullable = false, unique = true)
    private int idAluno;
    @Column(name = "idTurma", nullable = false, unique = true)
    private int idTurma;
}
