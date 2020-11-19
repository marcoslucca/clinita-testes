package br.com.fundatec.clinicaveterinaria.repository;

import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import br.com.fundatec.clinicaveterinaria.domain.Tutor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query("select c from Tutor c where c.nome = :nome")
    Optional<Tutor> findByNome(@Param("nome") String nome);

    @Query("select t.cachorros from Tutor t where t.nome = :nome")
    List<Cachorro> findAllCachorros(@Param("nome") String nome);
}
