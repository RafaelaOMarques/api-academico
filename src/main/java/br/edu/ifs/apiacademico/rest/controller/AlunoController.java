package br.edu.ifs.apiacademico.rest.controller;

import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.model.AlunoModel;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.rest.dto.AlunoDto;
import br.edu.ifs.apiacademico.service.AlunoService;
import br.edu.ifs.apiacademico.service.MatriculaService;
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
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MatriculaService matriculaService;


    //ENDPOINT /ALUNO

    @GetMapping
    public ResponseEntity<List<AlunoDto>> ObterTodosAlunos() {
        List<AlunoDto> alunoDtoList = alunoService.ObterTodos();
        return ResponseEntity.ok(alunoDtoList);
    }

    @PostMapping
    public ResponseEntity<AlunoDto> CadastrarNovoAluno(@Valid @RequestBody AlunoModel alunoModel, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        try{
            ValidadorCpfUtil.validarCpf(alunoModel.getCpf());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

        AlunoDto alunoDto = alunoService.CadastrarNovoAluno(alunoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(alunoDto);
    }


    //ENDPOINT /ALUNO/NOMES

    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<AlunoDto>> ObterAlunosPorNome(@PathVariable("nome") String nome) {
        List<AlunoDto> alunoDtoList = alunoService.ObterAlunosPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(alunoDtoList);
    }


    //ENDPOINT /ALUNO/NOMES/ORDENADOS

    @GetMapping("/nomes/ordenados")
    public ResponseEntity<List<AlunoDto>> ObterAlunosPorNomeOrdemCrescente() {
        List<AlunoDto> alunoDtoList = alunoService.ListarAlunosPorNomeCrescente();
        return ResponseEntity.status(HttpStatus.OK).body(alunoDtoList);
    }

    //ENDPOINT /ALUNO/CPF/{cpf}

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AlunoDto> ObterAlunoPorCpf(@PathVariable("cpf") String cpf) {
        AlunoDto alunoDto = alunoService.ObterAlunoPorCpf(cpf);
        return ResponseEntity.ok(alunoDto);
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> DeletarPorCpf(@PathVariable("cpf") String cpf) {
        alunoService.DeletarPorCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    //ENDPOINT /ALUNO/MATRICULA/{matricula}

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<AlunoDto> ObterPorMatricula(@PathVariable("matricula") int matricula) {
        AlunoDto alunoDto = alunoService.ObterPorMatricula(matricula);
        return ResponseEntity.ok(alunoDto);
    }

    @PutMapping("/matricula/{matricula}")
    public ResponseEntity<AlunoDto> AtualizarAluno(@Valid @RequestBody AlunoModel alunoModel
            , @PathVariable("matricula") int matricula, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }

        AlunoDto alunoDto = alunoService.AtualizarAluno(alunoModel, matricula);
        return ResponseEntity.ok(alunoDto);
    }

    @DeleteMapping("/matricula/{matricula}")
    public ResponseEntity<Void> DeletarPorMatricula(@PathVariable("matricula") int matricula) {
        alunoService.DeletarPorMatricula(matricula);
        return ResponseEntity.noContent().build();
    }

    
    //ENDPOINT /ALUNO/EMAIL/{email}

    @GetMapping("/email/{email}")
    public ResponseEntity<AlunoDto> ObterAlunoPorEmail(@PathVariable("email") String email) {
        AlunoDto alunoDto = alunoService.ObterAlunoPorEmail(email);
        return ResponseEntity.ok(alunoDto);
    }


    //ENDPOINT /ALUNO/DATANASCIMENTO
    @GetMapping("/datanascimento")
    public ResponseEntity<List<AlunoDto>> ListarAlunosPorDataNascimentoDescrescente() {
        List<AlunoDto> alunoDtoList = alunoService.ListarAlunosPorDataNascimentoDescrescente();
        return ResponseEntity.ok(alunoDtoList);
    }
}
