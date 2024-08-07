package br.edu.ifs.apiacademico.rest.controller;

import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.rest.dto.MatriculaDto;
import br.edu.ifs.apiacademico.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping("/{IdAluno}")
    public ResponseEntity<MatriculaDto> findById(@PathVariable("idAluno") int idAluno) {
        MatriculaDto matriculaDto = matriculaService.ObterPorIdAluno(idAluno);
        return ResponseEntity.ok(matriculaDto);
    }

    @PostMapping
    public ResponseEntity<MatriculaDto> create(@Valid @RequestBody MatriculaModel matriculaModel, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        MatriculaDto matriculaDto = matriculaService.Salvar(matriculaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        matriculaService.Deletar(id);
        return ResponseEntity.noContent().build();
    }
}
