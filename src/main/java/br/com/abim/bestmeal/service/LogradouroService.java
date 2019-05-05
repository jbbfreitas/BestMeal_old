package br.com.abim.bestmeal.service;

import br.com.abim.bestmeal.domain.Logradouro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Logradouro.
 */
public interface LogradouroService {

    /**
     * Save a logradouro.
     *
     * @param logradouro the entity to save
     * @return the persisted entity
     */
    Logradouro save(Logradouro logradouro);

    /**
     * Get all the logradouros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Logradouro> findAll(Pageable pageable);


    /**
     * Get the "id" logradouro.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Logradouro> findOne(Long id);

    /**
     * Delete the "id" logradouro.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
