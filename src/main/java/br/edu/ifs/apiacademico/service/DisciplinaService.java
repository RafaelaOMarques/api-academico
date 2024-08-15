package br.edu.ifs.apiacademico.service;

import br.edu.ifs.apiacademico.exceptions.ConstraintException;
import br.edu.ifs.apiacademico.exceptions.DataIntegrityException;
import br.edu.ifs.apiacademico.exceptions.ObjectNotFoundException;
import br.edu.ifs.apiacademico.model.AlunoModel;
import br.edu.ifs.apiacademico.model.DisciplinaModel;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.repository.DisciplinaRepository;
import br.edu.ifs.apiacademico.rest.dto.DisciplinaDto;
import br.edu.ifs.apiacademico.rest.dto.MatriculaDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<DisciplinaDto> ObterTodasDisciplinas() {
        List<DisciplinaModel> disciplinaList = disciplinaRepository.findAll();
        return disciplinaList.stream()
                .map(disciplina -> modelMapper.map(disciplina, DisciplinaDto.class))
                .collect(Collectors.toList());
    }
    public DisciplinaDto ObterDisciplinaPorId(int id) {
        Optional<DisciplinaModel> disciplinaOptional = disciplinaRepository.findById(id);
        DisciplinaModel disciplinaModel = disciplinaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Disciplina não encontrada! Disciplina:  " + id));
        return modelMapper.map(disciplinaModel, DisciplinaDto.class);
    }

    @Transactional
    public DisciplinaDto CadastrarNovaDisciplina(DisciplinaModel disciplinaModel) {
        if (disciplinaModel.getProfessor() == null) {
            throw new ConstraintException("Professor não encontrado. Disciplina não pode ser cadastrada sem um professor associado.");
        }

        try {
            disciplinaRepository.save(disciplinaModel);
            return modelMapper.map(disciplinaModel, DisciplinaDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("ERRO: Disciplina verificar atributos necessários para o cadastramento da disciplina");
        }
    }


    @Transactional
    public void DeletarPorId(int id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
        } else {
            throw new DataIntegrityException("ERRO: Disciplina não encontrada! Disciplina:  " + id);
        }
    }

    public DisciplinaDto ObterDisciplinaPorNome(String nome) {
        Optional<DisciplinaModel> disciplinaOptional = disciplinaRepository.findByNomeContaining(nome);
        DisciplinaModel disciplinaModel = disciplinaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Disciplina não encontrada! Nome: " + nome));
        return modelMapper.map(disciplinaModel, DisciplinaDto.class);
    }

    public List<DisciplinaDto> ListarDisciplinasOrdenadasPorNome() {
        List<DisciplinaModel> disciplinaList = disciplinaRepository.findByOrderByNomeAsc();
        return disciplinaList.stream()
                .map(disciplina -> modelMapper.map(disciplina, DisciplinaDto.class))
                .collect(Collectors.toList());
    }

    public DisciplinaDto ObterDisciplinasPorNomeEOrdenadas(String nome){
        Optional<DisciplinaModel> optionalDisciplinaList = disciplinaRepository.findByNomeOrderByNomeAsc(nome);
        DisciplinaModel disciplinaModel = optionalDisciplinaList.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Disciplina(s) não encontrada(s)! Nome: " + nome));
        return modelMapper.map(disciplinaModel, DisciplinaDto.class);
    }

    public List<DisciplinaDto> ObterDisciplinasPorCreditos(int numeroCreditos){
        List<DisciplinaModel> disciplinaList = disciplinaRepository.findByNumeroCreditos(numeroCreditos);
        if (disciplinaList.isEmpty()) {
            throw new ObjectNotFoundException("ERRO: Não localizadas disciplinas com número de créditos de " + numeroCreditos);
        }
        return disciplinaList.stream()
                .map(disciplina -> modelMapper.map(disciplina, DisciplinaDto.class))
                .collect(Collectors.toList());
    }

    public List<DisciplinaDto> ListarDisciplinasOrdenadasPorCreditos() {
        List<DisciplinaModel> disciplinaList = disciplinaRepository.findByOrderByNumeroCreditosAsc();
        return disciplinaList.stream()
                .map(disciplina -> modelMapper.map(disciplina, DisciplinaDto.class))
                .collect(Collectors.toList());
    }

    public List<DisciplinaDto> ListarDisciplinaByProfessorId(int professorId) {
        List<DisciplinaModel> disciplinaList = disciplinaRepository.findByProfessorId(professorId);
        return disciplinaList.stream()
                .map(disciplina -> modelMapper.map(disciplina, DisciplinaDto.class))
                .collect(Collectors.toList());
    }

}