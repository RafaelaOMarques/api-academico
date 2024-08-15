package br.edu.ifs.apiacademico.rest.controller;

import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.model.AlunoModel;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.model.TurmaModel;
import br.edu.ifs.apiacademico.repository.AlunoRepository;
import br.edu.ifs.apiacademico.rest.dto.MatriculaDto;
import br.edu.ifs.apiacademico.rest.dto.TurmaDto;
import br.edu.ifs.apiacademico.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static br.edu.ifs.apiacademico.util.GeradorIdUtil.gerarIdentificadorUnico;
import static br.edu.ifs.apiacademico.util.GerardorMatriculaUtil.gerarMatricula;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<MatriculaDto>> ObterTodasMatriculas() {
        List<MatriculaDto> matriculaDtoList = matriculaService.ObterTodas();
        return ResponseEntity.ok(matriculaDtoList);
    }

    @GetMapping("/{IdAluno}")
    public ResponseEntity<MatriculaDto> findById(@PathVariable("idAluno") int idAluno) {
        MatriculaDto matriculaDto = matriculaService.ObterMatriculaPorIdAluno(idAluno);
        return ResponseEntity.ok(matriculaDto);
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaDto>> ListarMatriculasByAlunoId(@PathVariable("alunoId") int alunoId) {
        List<MatriculaDto> matriculaList = matriculaService.ListarMatriculaByAlunoId(alunoId);
        return ResponseEntity.ok(matriculaList);
    }

    @PostMapping
    public ResponseEntity<MatriculaDto> CadastrarNovaMatricula(@Valid @RequestBody  MatriculaModel matriculaModel, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }

        int ordemMatricula = gerarMatricula();
        matriculaModel.setMatricula(ordemMatricula);

        MatriculaDto novaMatricula = matriculaService.GerarMatricula(matriculaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDto> AtualizarMatricula(
            @Valid
            @PathVariable("id") int id,
            @RequestBody MatriculaModel matriculaModel, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        MatriculaDto matriculaDto = matriculaService.AtualizarMatricula(id, matriculaModel);
        return ResponseEntity.ok(matriculaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        matriculaService.Deletar(id);
        return ResponseEntity.noContent().build();
    }
}
