package br.edu.ifs.apiacademico.service;
import br.edu.ifs.apiacademico.exceptions.DataIntegrityException;
import br.edu.ifs.apiacademico.exceptions.ObjectNotFoundException;
import br.edu.ifs.apiacademico.model.ProfessorModel;
import br.edu.ifs.apiacademico.repository.ProfessorRepository;
import br.edu.ifs.apiacademico.rest.dto.ProfessorDisciplinasDto;
import br.edu.ifs.apiacademico.rest.dto.ProfessorDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProfessorDisciplinasDto> ObterTodos() {
        List<ProfessorModel> professorList = professorRepository.findAll();
        return professorList.stream()
            .map(professor -> modelMapper.map(professor, ProfessorDisciplinasDto.class))
            .collect(Collectors.toList());
    }

    public ProfessorDto ObterPorMatricula(int matricula) {
        Optional<ProfessorModel> professorModelOptional = professorRepository
                .findByMatricula(matricula);
        ProfessorModel professorModel = professorModelOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        return modelMapper.map(professorModel, ProfessorDto.class);
    }

    public List<ProfessorDto> ObterProfessoresPorNome(String nome) {
        List<ProfessorModel> professorList = professorRepository.findByNomeContaining(nome);
        return professorList.stream()
            .map(professor -> modelMapper.map(professor, ProfessorDto.class))
            .collect(Collectors.toList());
    }

    public List<ProfessorDto> ListarProfessoresPorNomeAscedente() {
        List<ProfessorModel> professorList = professorRepository.findByOrderByNomeAsc();
        return professorList.stream()
            .map(professor -> modelMapper.map(professor, ProfessorDto.class))
            .collect(Collectors.toList());
    }
    
    public List<ProfessorDto> ObterProfessorsPorNomeEOrdemDescrescente(String nome) {
        List<ProfessorModel> professorList = professorRepository.findByNomeOrderByNomeAsc(nome);
        return professorList.stream()
            .map(professor -> modelMapper.map(professor, ProfessorDto.class))
            .collect(Collectors.toList());
    }

    public ProfessorDto ObterProfessorPorCpf(String cpf) {
        Optional<ProfessorModel> professorOptional = professorRepository.findByCpf(cpf);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
            new ObjectNotFoundException("ERRO: Não existe professor cadastrado com o CPF: " + cpf));

        return modelMapper.map(professorModel, ProfessorDto.class);
    }

    public ProfessorDto ObterProfessorPorEmail(String email) {
        Optional<ProfessorModel> professorOptional = professorRepository.findByEmail(email);
        ProfessorModel professorModel = professorOptional.orElseThrow(() ->
            new ObjectNotFoundException("ERRO: Não existe professor cadastrado com o email: " + email));

        return modelMapper.map(professorModel, ProfessorDto.class);
    }
    
    public List<ProfessorDto> ListarProfessorsPorDataNascimentoDescrescente() {
        List<ProfessorModel> professorList = professorRepository.findByOrderByDataNascimentoDesc();
        return professorList.stream()
            .map(professor -> modelMapper.map(professor, ProfessorDto.class))
            .collect(Collectors.toList());
    }


    @Transactional
    public ProfessorDto CadastrarNovoProfessor(ProfessorModel professorModel) {
        try {
            professorRepository.save(professorModel);
            return modelMapper.map(professorModel, ProfessorDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("ERRO: professor já cadastrado!");
        }
    }

    @Transactional
    public ProfessorDto AtualizarProfessor(ProfessorModel professorModel, int matricula) {
        Optional<ProfessorModel> professorExistente = professorRepository.findByMatricula(matricula);
        ProfessorModel professorAtualizado = professorExistente.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        professorAtualizado.setNome(professorModel.getNome());
        professorAtualizado.setEmail(professorModel.getEmail());
        professorRepository.save(professorAtualizado);

        return modelMapper.map(professorAtualizado, ProfessorDto.class);
    }

    @Transactional
    public void DeletarPorMatricula(int matricula) {
        if (professorRepository.existsByMatricula(matricula)) {
            professorRepository.deleteByMatricula(matricula);
        } else {
            throw new DataIntegrityException("ERRO: Matrícula não encontrada! Matrícula: " + matricula);
        }
    }

    @Transactional
    public void DeletarPorCpf(String cpf) {
        if (professorRepository.existsByCpf(cpf)) {
            professorRepository.deleteByCpf(cpf);
        } else {
            throw new DataIntegrityException("ERRO: CPF não encontrada! CPF: " + cpf);
        }
    }

    @Transactional
    public ProfessorDto Salvar(ProfessorModel professorModel) {
        try {
            professorRepository.save(professorModel);
            return modelMapper.map(professorModel, ProfessorDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("ERRO: Professor já cadastrado!");
        }
    }


    @Transactional
    public ProfessorDto Atualizar(ProfessorModel professorModel, int matricula) {
        Optional<ProfessorModel> professorExistente = professorRepository.findByMatricula(matricula);
        ProfessorModel professorAtualizado = professorExistente.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        professorAtualizado.setNome(professorModel.getNome());
        professorAtualizado.setEmail(professorModel.getEmail());
        professorRepository.save(professorAtualizado);

        return modelMapper.map(professorAtualizado, ProfessorDto.class);
    }

    @Transactional
    public void Deletar(int matricula) {
        if (professorRepository.existsByMatricula(matricula)) {
            professorRepository.deleteByMatricula(matricula);
        } else {
            throw new DataIntegrityException("ERRO: Matrícula não encontrada! Matrícula: " + matricula);
        }
    }
    
}
