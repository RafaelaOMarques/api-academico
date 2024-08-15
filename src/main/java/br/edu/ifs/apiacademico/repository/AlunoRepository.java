package br.edu.ifs.apiacademico.repository;
import br.edu.ifs.apiacademico.model.AlunoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {
    
    //SELECTS
    List<AlunoModel> findAll();

    Optional<AlunoModel> findById(Integer id);

    List<AlunoModel> findByNomeContaining(String nome);

    List<AlunoModel> findByOrderByNomeDesc();

    List<AlunoModel> findByOrderByNomeAsc();

    List<AlunoModel> findByNomeOrderByNomeAsc(String nome);

    Optional<AlunoModel> findByCpf(String cpf);

    Optional<AlunoModel> findByEmail(String email);

    List<AlunoModel> findByOrderByDataNascimentoDesc();

    @Query("SELECT aluno FROM AlunoModel aluno " +
            "JOIN aluno.matriculas matricula " +
            "WHERE matricula.matricula = :matricula")
    Optional<AlunoModel> findByMatricula(@Param("matricula") int matricula);

    //DELETES
    Boolean existsByCpf(String cpf);
    void deleteByCpf(String cpf);


    @Modifying
    @Transactional
    @Query("DELETE FROM AlunoModel aluno " +
            "WHERE EXISTS (SELECT 1 FROM aluno.matriculas matricula " +
            "               WHERE matricula.matricula = :matricula)")
    void deleteByMatricula(@Param("matricula") int matricula);

    @Query(value = "SELECT * FROM aluno a " + "WHERE a.email = :email", nativeQuery = true)
    List<AlunoModel> ObterAlunoPorEmail(@Param("email") String email);
}
