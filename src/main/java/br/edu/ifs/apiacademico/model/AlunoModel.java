package br.edu.ifs.apiacademico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="aluno")
public class AlunoModel {
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

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private Date dataNascimento;

    @Column(name = "celular", length = 14, nullable = false, unique = false)
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}", message = "Celular inválido")
    private String celular;

    @Column(name = "apelido", length = 255, nullable = true)
    private String apelido;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MatriculaModel> matriculas;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    @JsonBackReference
    private TurmaModel turma;

//    @ManyToMany(mappedBy = "alunos", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<TurmaModel> turma;

}
