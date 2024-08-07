package br.edu.ifs.apiacademico.rest.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class ProfessorDto {
    private Long matricula;
    private String nome;
}
