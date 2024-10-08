package br.edu.ifs.apiacademico.rest.dto;

import br.edu.ifs.apiacademico.model.AlunoModel;
import br.edu.ifs.apiacademico.model.DisciplinaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDto {
    private int id;
    private int turmaId;
    private DisciplinaDto disciplina;
    private List<AlunoDto> alunos;

}
