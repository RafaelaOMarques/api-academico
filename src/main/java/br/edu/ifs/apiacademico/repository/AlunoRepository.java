package br.edu.ifs.apiacademico.repository;
import br.edu.ifs.apiacademico.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {
    
    //SELECTS
    List<AlunoModel> findAll();

    Optional<AlunoModel> findByMatricula(int matricula);

    List<AlunoModel> findByNomeContaining(String nome);

    List<AlunoModel> findByOrderByNomeDesc();

    List<AlunoModel> findByOrderByNomeAsc();


    List<AlunoModel> findByNomeOrderByNomeAsc(String nome);

    Optional<AlunoModel> findByCpf(String cpf);

    Optional<AlunoModel> findByEmail(String email);

    List<AlunoModel> findByOrderByDataNascimentoDesc();

    //DELETES
    Boolean existsByMatricula(int matricula);
    Boolean existsByCpf(String cpf);
    void deleteByMatricula(int matricula);
    void deleteByCpf(String cpf);


    @Query(value = "SELECT * FROM aluno a " + "WHERE a.email = :email", nativeQuery = true)
    List<AlunoModel> ObterAlunoPorEmail(@Param("email") String email);
}
