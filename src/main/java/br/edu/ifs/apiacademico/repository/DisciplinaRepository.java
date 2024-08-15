package br.edu.ifs.apiacademico.repository;

import br.edu.ifs.apiacademico.model.DisciplinaModel;
import br.edu.ifs.apiacademico.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Integer> {

    List<DisciplinaModel> findAll();
    Optional<DisciplinaModel> findById(int id);

//    Boolean existsById(int id);

    Optional<DisciplinaModel> findByNomeContaining(String nome);

    List<DisciplinaModel> findByOrderByNomeAsc();

    Optional<DisciplinaModel> findByNomeOrderByNomeAsc(String nome);

    List<DisciplinaModel> findByNumeroCreditos(int numeroCreditos);

    List<DisciplinaModel> findByOrderByNumeroCreditosAsc();

    void deleteById(int id);

    List<DisciplinaModel> findByProfessorId(int professorId);
//    List<DisciplinaModel> findByAlunoId(int alunoId);

}
