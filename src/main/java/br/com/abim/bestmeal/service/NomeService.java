package br.com.abim.bestmeal.service;

import br.com.abim.bestmeal.domain.Nome;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Nome.
 */
public interface NomeService {

    /**
     * Save a nome.
     *
     * @param nome the entity to save
     * @return the persisted entity
     */
    Nome save(Nome nome);

    /**
     * Get all the nomes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Nome> findAll(Pageable pageable);


    /**
     * Get the "id" nome.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Nome> findOne(Long id);

    /**
     * Delete the "id" nome.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
