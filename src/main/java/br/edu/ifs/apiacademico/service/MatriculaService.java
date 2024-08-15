package br.edu.ifs.apiacademico.service;

import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.exceptions.DataIntegrityException;
import br.edu.ifs.apiacademico.exceptions.ObjectNotFoundException;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.model.TurmaModel;
import br.edu.ifs.apiacademico.repository.MatriculaRepository;
import br.edu.ifs.apiacademico.rest.dto.MatriculaDto;
import br.edu.ifs.apiacademico.rest.dto.TurmaDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.edu.ifs.apiacademico.util.GerardorMatriculaUtil.gerarMatricula;

/**
 * Classe responsável por prover os serviços relacionados a operações de Matricula.
 */
@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MatriculaDto> ObterTodas() {
        List<MatriculaModel> matriculaList = matriculaRepository.findAll();
        return matriculaList.stream()
                .map(matricula -> modelMapper.map(matricula, MatriculaDto.class))
                .collect(Collectors.toList());
    }

    public Optional<MatriculaModel> findById(int id){
        return matriculaRepository.findById(id);
    }

    public MatriculaDto ObterMatriculaPorIdAluno(int idAluno) {
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findByIdAluno(idAluno);
        MatriculaModel matriculaModel = matriculaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matricula não encontrada! IdAluno: " + idAluno));

        return modelMapper.map(matriculaModel, MatriculaDto.class);
    }


    public List<MatriculaDto> ListarMatriculaByAlunoId(int alunoId) {
        List<MatriculaModel> matriculaList = matriculaRepository.findByAlunoId(alunoId);
        return matriculaList.stream()
                .map(matricula -> modelMapper.map(matricula, MatriculaDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public MatriculaDto GerarMatricula(MatriculaModel matriculaModel) {
        if (matriculaModel.getAluno() == null) {
            throw new ConstraintException("Aluno não encontrado. Matrícula não pode ser gerada sem um aluno associado.");
        }

        try {
            matriculaModel.setMatriculaAtiva(true);
            matriculaRepository.save(matriculaModel);
            return modelMapper.map(matriculaModel, MatriculaDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("ERRO: Para gerar uma matricula é necessário informar no body um aluno cadastrado!");
        }
    }


    @Transactional
    public MatriculaDto AtualizarMatricula(int idMatricula, MatriculaModel matriculaModel){
        Optional<MatriculaModel> matriculaExistente = matriculaRepository.findMatriculaModelByMatricula(idMatricula);
        MatriculaModel matriculaAtualizada = matriculaExistente.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + idMatricula));

        matriculaAtualizada.setMatriculaAtiva(matriculaModel.isMatriculaAtiva());
        if (matriculaModel.getAluno() != null) {
            matriculaAtualizada.setAluno(matriculaModel.getAluno());
        }

        MatriculaModel matriculaSalva = matriculaRepository.save(matriculaAtualizada);
        MatriculaDto matriculaDto = modelMapper.map(matriculaSalva, MatriculaDto.class);
        matriculaDto.setIdAluno(matriculaSalva.getAluno().getId());

        return matriculaDto;
    }

    @Transactional
    public void Deletar(int id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
        } else {
            throw new DataIntegrityException("ERRO: Matrícula não encontrada! Matrícula: " + id);
        }
    }
}