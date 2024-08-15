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
//        String nomeProfessor = turma.getProfessor() != null ? turma.getProfessor().getNome() : "Desconhecido";
//        String nomeDisciplina = turma.getDisciplina() != null ? turma.getDisciplina().getNome() : "Desconhecida";

        TurmaDto turmaDto = modelMapper.map(turma, TurmaDto.class);
//        turmaDto.setNomeProfessor(nomeProfessor);
//        turmaDto.setNomeDisciplina(nomeDisciplina);

        return turmaDto;
    }

    @Transactional
    public TurmaDto CadastrarNovaTurma(TurmaModel turma) {
        DisciplinaModel disciplina = disciplinaRepository.findById(turma.getDisciplina().getId())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
        turma.setDisciplina(disciplina);

//        ProfessorModel professor = professorRepository.findById(turma.getProfessor().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));
//        turma.setProfessor(professor);

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

//    public TurmaDto createTurma(TurmaCreateDto turmaCreateDto) {
//        TurmaModel turma = new TurmaModel();
//        turma.setNome(turmaCreateDto.getNome());
//
//        DisciplinaModel disciplina = disciplinaRepository.findById(turmaCreateDto.getDisciplinaId())
//                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
//        turma.setDisciplina(disciplina);
//
//        ProfessorModel professor = professorRepository.findById(turmaCreateDto.getProfessorId())
//                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));
//        turma.setProfessor(professor);
//
//        List<AlunoModel> alunos = alunoRepository.findAllById(turmaCreateDto.getAlunoIds());
//        turma.setAlunos(alunos);
//
//        turma = turmaRepository.save(turma);
//        return modelMapper.map(turma, TurmaDto.class);
//    }

    @Transactional
    public void DeletarTurmaPorId(int id) {
        Optional<TurmaModel> turmaOptional = turmaRepository.findById(id);

        if (turmaOptional.isPresent()) {
            TurmaModel turma = turmaOptional.get();

//            if (turma.getProfessor() == null) {
//                turmaRepository.deleteById(id);
//            } else {
//                throw new IllegalStateException("Não é possível excluir a turma porque há um professor vinculado.");
//            }
        } else {
            throw new IllegalArgumentException("Turma não encontrada com o ID: " + id);
        }
    }


//    public List<TurmaDto> ListarTurmasByProfessorId(int professorId) {
//        List<TurmaModel> turmaList = turmaRepository.findByProfessorId(professorId);
//        return turmaList.stream()
//                .map(turma -> modelMapper.map(turma, TurmaDto.class))
//                .collect(Collectors.toList());
//    }


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

//    public List<TurmaDto> ListarTurmasByDisciplinasIdOrdenadasPorProfessor(int disciplinaId){
//        List<TurmaModel> turmaList = turmaRepository.findByDisciplinaIdOrderByProfessorNomeAsc(disciplinaId
//        );
//        if (turmaList.isEmpty()) {
//            throw new ObjectNotFoundException("ERRO: Não localizada turma com Disciplina id " + disciplinaId);
//        }
//        return turmaList.stream()
//                .map(turma -> modelMapper.map(turma, TurmaDto.class))
//                .collect(Collectors.toList());
//    }

//    public List<TurmaDto> ListarTurmaPorDisciplinaEProfessor(int disciplinaId, int professorId){
//
//        List<TurmaModel> turmaListDisciplinaProfessor = turmaRepository.findByDisciplinaIdAndProfessorId(disciplinaId, professorId);
//        if (turmaListDisciplinaProfessor.isEmpty()) {
//            throw new ObjectNotFoundException("ERRO: Não localizada turma com disciplina id: " + disciplinaId + "ou professor id: " + professorId);
//        }
//        return turmaListDisciplinaProfessor.stream()
//                .map(turma -> modelMapper.map(turma, TurmaDto.class))
//                .collect(Collectors.toList());
//    }
}