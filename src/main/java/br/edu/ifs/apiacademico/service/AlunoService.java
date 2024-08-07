package br.edu.ifs.apiacademico.service;
import br.edu.ifs.apiacademico.exceptions.DataIntegrityException;
import br.edu.ifs.apiacademico.exceptions.ObjectNotFoundException;
import br.edu.ifs.apiacademico.model.AlunoModel;
import br.edu.ifs.apiacademico.repository.AlunoRepository;
import br.edu.ifs.apiacademico.rest.dto.AlunoDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AlunoDto> ObterTodos() {
        List<AlunoModel> alunoList = alunoRepository.findAll();
        return alunoList.stream()
            .map(aluno -> modelMapper.map(aluno, AlunoDto.class))
            .collect(Collectors.toList());
    }

    public AlunoDto ObterPorMatricula(int matricula) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(matricula);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        return modelMapper.map(alunoModel, AlunoDto.class);
    }

    public List<AlunoDto> ObterAlunosPorNome(String nome) {
        List<AlunoModel> alunoList = alunoRepository.findByNomeContaining(nome);
        return alunoList.stream()
            .map(aluno -> modelMapper.map(aluno, AlunoDto.class))
            .collect(Collectors.toList());
    }

    public List<AlunoDto> ListarAlunosPorNomeCrescente() {
        List<AlunoModel> alunoList = alunoRepository.findByOrderByNomeAsc();
        return alunoList.stream()
            .map(aluno -> modelMapper.map(aluno, AlunoDto.class))
            .collect(Collectors.toList());
    }
    
    public List<AlunoDto> ObterAlunosPorNomeEOrdemCrescente(String nome) {
        List<AlunoModel> alunoList = alunoRepository.findByNomeOrderByNomeAsc(nome);
        return alunoList.stream()
            .map(aluno -> modelMapper.map(aluno, AlunoDto.class))
            .collect(Collectors.toList());
    }

    public AlunoDto ObterAlunoPorCpf(String cpf) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByCpf(cpf);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() ->
            new ObjectNotFoundException("ERRO: Não existe Aluno cadastrado com o CPF: " + cpf));

        return modelMapper.map(alunoModel, AlunoDto.class);
    }

    public AlunoDto ObterAlunoPorEmail(String email) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByEmail(email);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() ->
            new ObjectNotFoundException("ERRO: Não existe Aluno cadastrado com o email: " + email));

        return modelMapper.map(alunoModel, AlunoDto.class);
    }
    
    public List<AlunoDto> ListarAlunosPorDataNascimentoDescrescente() {
        List<AlunoModel> alunoList = alunoRepository.findByOrderByDataNascimentoDesc();
        return alunoList.stream()
            .map(aluno -> modelMapper.map(aluno, AlunoDto.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDto CadastrarNovoAluno(AlunoModel alunoModel) {
        try {
            alunoRepository.save(alunoModel);
            return modelMapper.map(alunoModel, AlunoDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("ERRO: Aluno já cadastrado!");
        }
    }

    @Transactional
    public AlunoDto AtualizarAluno(AlunoModel alunoModel, int matricula) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findByMatricula(matricula);
        AlunoModel alunoAtualizado = alunoExistente.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        alunoAtualizado.setNome(alunoModel.getNome());
        alunoAtualizado.setEmail(alunoModel.getEmail());
        alunoRepository.save(alunoAtualizado);

        return modelMapper.map(alunoAtualizado, AlunoDto.class);
    }

    @Transactional
    public void DeletarPorMatricula(int matricula) {
        if (alunoRepository.existsByMatricula(matricula)) {
            alunoRepository.deleteByMatricula(matricula);
        } else {
            throw new DataIntegrityException("ERRO: Matrícula não encontrada! Matrícula: " + matricula);
        }
    }

    @Transactional
    public void DeletarPorCpf(String cpf) {
        if (alunoRepository.existsByCpf(cpf)) {
            alunoRepository.deleteByCpf(cpf);
        } else {
            throw new DataIntegrityException("ERRO: CPF não encontrada! CPF: " + cpf);
        }
    }
}