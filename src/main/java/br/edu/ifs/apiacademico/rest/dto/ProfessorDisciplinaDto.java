package br.edu.ifs.apiacademico.rest.dto;

import br.edu.ifs.apiacademico.repository.ProfessorDisciplinaProjection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ProfessorDisciplinaDto {
    private int matricula;
    private String nomeProfessor;
    private List<ProfessorDisciplinaProjection> DisplinasLecionadas;
}
