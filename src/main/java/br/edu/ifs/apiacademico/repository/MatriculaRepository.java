package br.edu.ifs.apiacademico.repository;

import br.edu.ifs.apiacademico.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel, Integer> {

    Optional<MatriculaModel> findByIdAluno(int idAluno);

    Boolean existsById(int id);

    void deleteById(int id);


}
