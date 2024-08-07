package br.edu.ifs.apiacademico.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDto {
    private int id;
    private Date dataInicio;
    private Date dataFim;
    private String nomeProfessor;
    private String nomeDisciplina;
}
