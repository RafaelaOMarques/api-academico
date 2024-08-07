package br.edu.ifs.apiacademico.service;

import br.edu.ifs.apiacademico.exceptions.DataIntegrityException;
import br.edu.ifs.apiacademico.exceptions.ObjectNotFoundException;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.repository.MatriculaRepository;
import br.edu.ifs.apiacademico.rest.dto.MatriculaDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe responsável por prover os serviços relacionados a operações de Matricula.
 */
@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtém um aluno pelo número de matrícula.
     *
     * @param matricula O número de matrícula do aluno a ser obtido.
     * @throws ObjectNotFoundException Caso a matricula não seja encontrado no banco de dados.
     */
    public MatriculaDto ObterPorIdAluno(int idAluno) {
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findByIdAluno(idAluno);
        MatriculaModel matriculaModel = matriculaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matricula não encontrada! IdAluno: " + idAluno));

        return modelMapper.map(matriculaModel, MatriculaDto.class);
    }

    /**
     * Salva uma nova matricula de aluno no banco de dados.
     *
     * @param matriculaModel O objeto MatriculaModel a ser salvo.
     * @return Um objeto MatriculaDto correspondente a nova matricula de aluno salva.
     * @throws DataIntegrityException Caso o aluno já esteja cadastrado no banco de dados.
     */
    @Transactional
    public MatriculaDto Salvar(MatriculaModel matriculaModel) {
        try {
            matriculaRepository.save(matriculaModel);
            return modelMapper.map(matriculaModel, MatriculaDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("ERRO: Aluno já cadastrado!");
        }
    }

    /**
     * Deleta uma matricula de aluno do banco de dados pelo número de matrícula.
     *
     * @param matricula O id de matrícula do aluno a ser deletado.
     * @throws DataIntegrityException Caso a matrícula do aluno não seja encontrada no banco de dados.
     */
    @Transactional
    public void Deletar(int id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
        } else {
            throw new DataIntegrityException("ERRO: Matrícula não encontrada! Matrícula: " + id);
        }
    }
}