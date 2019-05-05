package br.com.abim.bestmeal.service.impl;

import br.com.abim.bestmeal.service.LogradouroService;
import br.com.abim.bestmeal.domain.Logradouro;
import br.com.abim.bestmeal.repository.LogradouroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Logradouro.
 */
@Service
@Transactional
public class LogradouroServiceImpl implements LogradouroService {

    private final Logger log = LoggerFactory.getLogger(LogradouroServiceImpl.class);

    private LogradouroRepository logradouroRepository;

    public LogradouroServiceImpl(LogradouroRepository logradouroRepository) {
        this.logradouroRepository = logradouroRepository;
    }

    /**
     * Save a logradouro.
     *
     * @param logradouro the entity to save
     * @return the persisted entity
     */
    @Override
    public Logradouro save(Logradouro logradouro) {
        log.debug("Request to save Logradouro : {}", logradouro);
        return logradouroRepository.save(logradouro);
    }

    /**
     * Get all the logradouros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Logradouro> findAll(Pageable pageable) {
        log.debug("Request to get all Logradouros");
        return logradouroRepository.findAll(pageable);
    }


    /**
     * Get one logradouro by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Logradouro> findOne(Long id) {
        log.debug("Request to get Logradouro : {}", id);
        return logradouroRepository.findById(id);
    }

    /**
     * Delete the logradouro by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Logradouro : {}", id);
        logradouroRepository.deleteById(id);
    }
}
