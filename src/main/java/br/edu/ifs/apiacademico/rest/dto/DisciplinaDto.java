package br.edu.ifs.apiacademico.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDto {
    private int id;
    private String nome;
    private int numeroCreditos;
}
