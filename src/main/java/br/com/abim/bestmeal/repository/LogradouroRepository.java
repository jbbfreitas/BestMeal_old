package br.com.abim.bestmeal.repository;

import br.com.abim.bestmeal.domain.Logradouro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Logradouro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {

}
