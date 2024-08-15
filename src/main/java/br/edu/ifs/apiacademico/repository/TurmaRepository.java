package br.edu.ifs.apiacademico.repository;

import br.edu.ifs.apiacademico.model.TurmaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel, Integer> {

    List<TurmaModel> findByDisciplinaId(int disciplinaId);


}
