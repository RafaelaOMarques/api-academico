package br.edu.ifs.apiacademico.rest.dto;

import br.edu.ifs.apiacademico.model.MatriculaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    private int id;
    private String nome;
    private List<MatriculaModel> matriculas;

}