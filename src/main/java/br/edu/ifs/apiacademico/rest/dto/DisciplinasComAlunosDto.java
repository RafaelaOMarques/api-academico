package br.edu.ifs.apiacademico.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinasComAlunosDto {
    private DisciplinaDto disciplina;
    private List<AlunoDto> alunos;
}
