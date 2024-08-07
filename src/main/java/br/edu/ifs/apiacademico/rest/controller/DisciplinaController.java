package br.edu.ifs.apiacademico.rest.controller;

import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.model.DisciplinaModel;
import br.edu.ifs.apiacademico.rest.dto.AlunoDto;
import br.edu.ifs.apiacademico.rest.dto.DisciplinaDto;
import br.edu.ifs.apiacademico.service.DisciplinaService;
import br.edu.ifs.apiacademico.util.ValidadorCpfUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static br.edu.ifs.apiacademico.util.GerardorMatriculaUtil.gerarMatricula;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;


    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> BuscarDisciplinasPorId(@PathVariable("id") int id) {
        DisciplinaDto disciplinaDto = disciplinaService.ObterDisciplinaPorId(id);
        return ResponseEntity.ok(disciplinaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarDisciplinaPorId(@PathVariable("id") int id) {
        disciplinaService.DeletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDto>> ListarTodasDisciplinas() {
        List<DisciplinaDto> disciplinaDtoList = disciplinaService.ObterTodasDisciplinas();
        return ResponseEntity.ok(disciplinaDtoList);
    }

    @PostMapping
    public ResponseEntity<DisciplinaDto> CadastrarNovaDisciplina(@Valid @RequestBody DisciplinaModel disciplinaModel, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        DisciplinaDto disciplinaDto = disciplinaService.CadastrarNovaDisciplina(disciplinaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaDto);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<DisciplinaDto> ObterDisciplinasPorNome(@PathVariable("nome") String nome) {
        DisciplinaDto disciplinaDtoList = disciplinaService.ObterDisciplinaPorNome(nome);
        return ResponseEntity.ok(disciplinaDtoList);
    }

    @GetMapping("/nome/ordenados")
    public ResponseEntity<List<DisciplinaDto>> ObterDisciplinasOrdenadas() {
        List<DisciplinaDto> disciplinaDtoList = disciplinaService.ListarDisciplinasOrdenadasPorNome();
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaDtoList);
    }

    @GetMapping("/nome/ordenados/{nome}")
    public ResponseEntity<DisciplinaDto> ObterDisciplinasPorNomeEOrdenadas(@PathVariable("nome") String nome) {
        DisciplinaDto disciplinaDtoList = disciplinaService.ObterDisciplinaPorNome(nome);
        return ResponseEntity.ok(disciplinaDtoList);
    }

    @GetMapping("/creditos/ordenados/{creditos}")
    public ResponseEntity<List<DisciplinaDto>> ObterDisciplinasPorNomeEOrdenadas(@PathVariable("creditos") int creditos) {
        List<DisciplinaDto> disciplinaDtoList = disciplinaService.ObterDisciplinasPorCreditos(creditos);
        return ResponseEntity.ok(disciplinaDtoList);
    }

    @GetMapping("/creditos")
    public ResponseEntity<List<DisciplinaDto>> ObterDisciplinasOrdenadasPorCredito() {
        List<DisciplinaDto> disciplinaDtoList = disciplinaService.ListarDisciplinasOrdenadasPorCreditos();
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaDtoList);
    }



}


