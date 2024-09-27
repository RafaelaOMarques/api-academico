package br.edu.ifs.apiacademico.rest.dto;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {
    private int matricula;
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private Date dataNascimento;
    private String celular;

}
