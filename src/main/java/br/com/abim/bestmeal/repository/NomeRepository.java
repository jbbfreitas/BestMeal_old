package br.com.abim.bestmeal.repository;

import br.com.abim.bestmeal.domain.Nome;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Nome entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NomeRepository extends JpaRepository<Nome, Long> {

}
