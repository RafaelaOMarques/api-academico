package br.edu.ifs.apiacademico.service;

import br.edu.ifs.apiacademico.exceptions.DataIntegrityException;
import br.edu.ifs.apiacademico.exceptions.ObjectNotFoundException;
import br.edu.ifs.apiacademico.model.AlunoModel;
import br.edu.ifs.apiacademico.model.DisciplinaModel;
import br.edu.ifs.apiacademico.model.ProfessorModel;
import br.edu.ifs.apiacademico.model.TurmaModel;
import br.edu.ifs.apiacademico.repository.AlunoRepository;
import br.edu.ifs.apiacademico.repository.DisciplinaRepository;
import br.edu.ifs.apiacademico.repository.ProfessorRepository;
import br.edu.ifs.apiacademico.repository.TurmaRepository;
import br.edu.ifs.apiacademico.rest.dto.DisciplinaDto;
import br.edu.ifs.apiacademico.rest.dto.TurmaDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TurmaDto> ObterTodasTurmas() {
        List<TurmaModel> turmaList = turmaRepository.findAll();
        return turmaList.stream()
                .map(turma -> modelMapper.map(turma, TurmaDto.class))
                .collect(Collectors.toList());
    }

    public TurmaDto ObterTurmaPorId(int id) {
        Optional<TurmaModel> turmaOptional = turmaRepository.findById(id);
        TurmaModel turma = turmaOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Turma não encontrada! Turma:  " + id));

        TurmaDto turmaDto = modelMapper.map(turma, TurmaDto.class);
        return turmaDto;
    }

    @Transactional
    public TurmaDto CadastrarNovaTurma(TurmaModel turma) {
        DisciplinaModel disciplina = disciplinaRepository.findById(turma.getDisciplina().getId())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
        turma.setDisciplina(disciplina);

        List<AlunoModel> alunosCadastrados = new ArrayList<>();

        for (AlunoModel aluno : turma.getAlunos()){
            AlunoModel alunoCadastrado = alunoRepository.findById(aluno.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
            alunosCadastrados.add(alunoCadastrado);
        }
        turma.setAlunos(alunosCadastrados);
        try {
            turmaRepository.save(turma);
            return modelMapper.map(turma, TurmaDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("ERRO: Verificar requisição sem todos atributos necessários!");
        }
    }


    @Transactional
    public void DeletarTurmaPorId(int id) {
        Optional<TurmaModel> turmaOptional = turmaRepository.findById(id);

        if (turmaOptional.isPresent()) {
            TurmaModel turma = turmaOptional.get();

        } else {
            throw new IllegalArgumentException("Turma não encontrada com o ID: " + id);
        }
    }



    public List<TurmaDto> ListarTurmasByDisciplinasId(int disciplinaId){
        List<TurmaModel> turmaList = turmaRepository.findByDisciplinaId(disciplinaId
        );
        if (turmaList.isEmpty()) {
            throw new ObjectNotFoundException("ERRO: Não localizada turma com Disciplina id " + disciplinaId);
        }
        return turmaList.stream()
                .map(turma -> modelMapper.map(turma, TurmaDto.class))
                .collect(Collectors.toList());
    }
}