package br.edu.ifs.apiacademico.rest.dto;

import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.model.TurmaModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private String celular;
    private String apelido;
    private List<MatriculaDto> matriculas;

}





