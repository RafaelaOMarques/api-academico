package br.edu.ifs.apiacademico.rest.dto;

import br.edu.ifs.apiacademico.rest.dto.DisciplinaDto;
import br.edu.ifs.apiacademico.rest.dto.ProfessorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDisciplinasDto {
    private ProfessorDto professor;
    private List<DisciplinaDto> disciplinas;
}
