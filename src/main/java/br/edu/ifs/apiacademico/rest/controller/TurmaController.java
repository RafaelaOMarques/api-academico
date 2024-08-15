package br.edu.ifs.apiacademico.rest.controller;

import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.model.TurmaModel;
import br.edu.ifs.apiacademico.rest.dto.TurmaDto;
import br.edu.ifs.apiacademico.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.edu.ifs.apiacademico.util.GeradorIdUtil.gerarIdentificadorUnico;
import static br.edu.ifs.apiacademico.util.GerardorMatriculaUtil.gerarMatricula;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;


    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> BuscarTurmaPorId(@PathVariable("id") int id) {
        TurmaDto turmaDto = turmaService.ObterTurmaPorId(id);
        return ResponseEntity.ok(turmaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarTurmaPorId(@PathVariable("id") int id) {
        turmaService.DeletarTurmaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TurmaDto>> ListarTodasTurmas() {
        List<TurmaDto> turmaDtoList = turmaService.ObterTodasTurmas();
        return ResponseEntity.ok(turmaDtoList);
    }

    @PostMapping
    public ResponseEntity<TurmaDto> CadastrarNovaTurma(@Valid @RequestBody TurmaModel turmaModel, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }

        int ordemTurma = gerarIdentificadorUnico();
        turmaModel.setTurmaId(ordemTurma);

        TurmaDto turmaDto = turmaService.CadastrarNovaTurma(turmaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaDto);
    }

//    @GetMapping("/professor/{professorId}")
//    public ResponseEntity<List<TurmaDto>> ListarTurmasByProfessorId(@PathVariable("professorId") int professorId) {
//        List<TurmaDto> turmaList = turmaService.ListarTurmasByProfessorId(professorId);
//        return ResponseEntity.ok(turmaList);
//    }

    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<TurmaDto>> ListarTurmasByDisciplinasId(@PathVariable("disciplinaId") int disciplinaId) {
        List<TurmaDto> turmaList = turmaService.ListarTurmasByDisciplinasId(disciplinaId);
        return ResponseEntity.ok(turmaList);
    }

//    @GetMapping("/disciplinaprofessores/{disciplinaId}")
//    public ResponseEntity<List<TurmaDto>> ListarTurmasByDisciplinasIdOrdenadasPorProfessor(@PathVariable("disciplinaId") int disciplinaId) {
//        List<TurmaDto> turmaList = turmaService.ListarTurmasByDisciplinasId(disciplinaId);
//        return ResponseEntity.ok(turmaList);
//    }

//    @GetMapping("/disciplina/{disciplinaId}/{professorId}")
//    public ResponseEntity<List<TurmaDto>> ListarTurmaPorDisciplinaEProfessor(@PathVariable("disciplinaId") int disciplinaId,
//                                                                                @PathVariable("professorId") int professorId) {
//        List<TurmaDto> turmaList = turmaService.ListarTurmaPorDisciplinaEProfessor(disciplinaId, professorId);
//        return ResponseEntity.ok(turmaList);
//    }


}


