package br.edu.ifs.apiacademico.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Table(name="professor")
public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;


    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    private String cpf;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    @Pattern(regexp = "^[a-z0-9]+(?:\\.[a-z0-9]+)*@[a-z0-9]+(?:\\.[a-z]+)+$", message = "Email inválido")
    private String email;

    @Column(name = "dataNascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private Date dataNascimento;

    @Column(name = "celular", length = 14, nullable = false, unique = false)
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}", message = "Celular inválido")
    private String celular;

    @Column(name = "matricula", nullable = false, unique = true)
    private int matricula;
}
