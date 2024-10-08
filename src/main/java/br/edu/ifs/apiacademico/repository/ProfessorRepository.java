package br.edu.ifs.apiacademico.repository;
import br.edu.ifs.apiacademico.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> {


//SELECTS
    List<ProfessorModel> findAll();

    Optional<ProfessorModel> findByMatricula(int matricula);

    List<ProfessorModel> findByNomeContaining(String nome);

    List<ProfessorModel> findByOrderByNomeDesc();

    List<ProfessorModel> findByOrderByNomeAsc();

    List<ProfessorModel> findByNomeOrderByNomeAsc(String nome);

    Optional<ProfessorModel> findByCpf(String cpf);


    Optional<ProfessorModel> findByEmail(String email);

    List<ProfessorModel> findByOrderByDataNascimentoDesc();


    //DELETES
    Boolean existsByMatricula(int matricula);
    Boolean existsByCpf(String cpf);
    void deleteByMatricula(int matricula);
    void deleteByCpf(String cpf);

}
