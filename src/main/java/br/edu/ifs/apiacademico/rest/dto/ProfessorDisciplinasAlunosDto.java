package br.edu.ifs.apiacademico.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDisciplinasAlunosDto {
    private ProfessorDto professor;
    private List<DisciplinasComAlunosDto> disciplinas;
}
