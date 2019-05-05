package br.com.abim.bestmeal.service.impl;

import br.com.abim.bestmeal.service.NomeService;
import br.com.abim.bestmeal.domain.Nome;
import br.com.abim.bestmeal.repository.NomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Nome.
 */
@Service
@Transactional
public class NomeServiceImpl implements NomeService {

    private final Logger log = LoggerFactory.getLogger(NomeServiceImpl.class);

    private NomeRepository nomeRepository;

    public NomeServiceImpl(NomeRepository nomeRepository) {
        this.nomeRepository = nomeRepository;
    }

    /**
     * Save a nome.
     *
     * @param nome the entity to save
     * @return the persisted entity
     */
    @Override
    public Nome save(Nome nome) {
        log.debug("Request to save Nome : {}", nome);
        return nomeRepository.save(nome);
    }

    /**
     * Get all the nomes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Nome> findAll(Pageable pageable) {
        log.debug("Request to get all Nomes");
        return nomeRepository.findAll(pageable);
    }


    /**
     * Get one nome by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Nome> findOne(Long id) {
        log.debug("Request to get Nome : {}", id);
        return nomeRepository.findById(id);
    }

    /**
     * Delete the nome by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nome : {}", id);
        nomeRepository.deleteById(id);
    }
}
