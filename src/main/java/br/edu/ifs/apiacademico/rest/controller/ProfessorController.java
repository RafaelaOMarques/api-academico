package br.edu.ifs.apiacademico.rest.controller;
import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.model.ProfessorModel;
import br.edu.ifs.apiacademico.rest.dto.ProfessorDisciplinasDto;
import br.edu.ifs.apiacademico.rest.dto.ProfessorDto;
import br.edu.ifs.apiacademico.service.ProfessorService;
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
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

//ENDPOINT /PROFESSOR

    @GetMapping
    public ResponseEntity<List<ProfessorDisciplinasDto>> ObterTodosProfessoresComDisciplinas() {
        List<ProfessorDisciplinasDto> professorList = professorService.ObterTodos();
        return ResponseEntity.ok(professorList);
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> CadastrarNovoProfessor(@Valid @RequestBody ProfessorModel professorModel, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        try{
            ValidadorCpfUtil.validarCpf(professorModel.getCpf());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        int matricula = gerarMatricula();
        professorModel.setMatricula(matricula);
        ProfessorDto professorDto = professorService.CadastrarNovoProfessor(professorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorDto);
    }

    //ENDPOINT /PROFESSOR/NOMES
    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<ProfessorDto>> ObterProfessoresPorNome(@PathVariable("nome") String nome) {
        List<ProfessorDto> professorDtoList = professorService.ObterProfessoresPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(professorDtoList);
    }

    //ENDPOINT /PROFESSOR/NOMES/ORDENADOS

    @GetMapping("/nomes/ordenados")
    public ResponseEntity<List<ProfessorDto>> ListarProfessoresPorNomeCrescente() {
        List<ProfessorDto> professorDtoList = professorService.ListarProfessoresPorNomeAscedente();
        return ResponseEntity.ok(professorDtoList);
    }


    //ENDPOINT /PROFESSOR/CPF/{cpf}

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ProfessorDto> ObterProfessorPorCpf(@PathVariable("cpf") String cpf) {
        ProfessorDto professorDto = professorService.ObterProfessorPorCpf(cpf);
        return ResponseEntity.ok(professorDto);
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> DeletarPorCpf(@PathVariable("cpf") String cpf) {
        professorService.DeletarPorCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    //ENDPOINT /PROFESSOR/MATRICULA/{matricula}

    @GetMapping("/matricula/{matricula}")
        public ResponseEntity<ProfessorDto> ObterProfessoresPorMatricula(@PathVariable("matricula") int matricula) {
        ProfessorDto professorDto = professorService.ObterPorMatricula(matricula);
        return ResponseEntity.ok(professorDto);
    }

    @PutMapping("/matricula/{matricula}")
    public ResponseEntity<ProfessorDto> AtualizarProfessor(@Valid @RequestBody ProfessorModel professorModel
            , @PathVariable("matricula") int matricula, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }

        ProfessorDto professorDto = professorService.AtualizarProfessor(professorModel, matricula);
        return ResponseEntity.ok(professorDto);
    }

    @DeleteMapping("/matricula/{matricula}")
    public ResponseEntity<Void> DeletarPorMatricula(@PathVariable("matricula") int matricula) {
        professorService.DeletarPorMatricula(matricula);
        return ResponseEntity.noContent().build();
    }

    
    //ENDPOINT /PROFESSOR/EMAIL/{email}

    @GetMapping("/email/{email}")
    public ResponseEntity<ProfessorDto> ObterProfessorPorEmail(@PathVariable("email") String email) {
        ProfessorDto professorDto = professorService.ObterProfessorPorEmail(email);
        return ResponseEntity.ok(professorDto);
    }


    //ENDPOINT /PROFESSOR/DATANASCIMENTO
    @GetMapping("/datanascimento")
    public ResponseEntity<List<ProfessorDto>> ListarProfessoresPorDataNascimentoDescrescente() {
        List<ProfessorDto> professorDtoList = professorService.ListarProfessorsPorDataNascimentoDescrescente();
        return ResponseEntity.ok(professorDtoList);
    }

}
