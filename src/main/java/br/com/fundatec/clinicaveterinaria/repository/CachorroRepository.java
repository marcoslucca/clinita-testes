package br.com.fundatec.clinicaveterinaria.repository;

import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long> {

    @Query("select c from Cachorro c where c.nome = :nome")
    Optional<Cachorro> findByNome(@Param("nome") String nome);

}
