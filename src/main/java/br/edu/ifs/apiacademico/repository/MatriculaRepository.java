package br.edu.ifs.apiacademico.repository;

import br.edu.ifs.apiacademico.model.AlunoModel;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import br.edu.ifs.apiacademico.model.TurmaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel, Integer> {

    Boolean existsById(int id);

    Optional<MatriculaModel> findMatriculaModelByMatricula(Integer id);


    List<MatriculaModel> findByAlunoId(int alunoId);


    void deleteById(int id);



    @Query("SELECT m FROM MatriculaModel m WHERE m.aluno.id = :idAluno")
    Optional<MatriculaModel> findByIdAluno(@Param("idAluno") int idAluno);
}
