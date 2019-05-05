package br.com.abim.bestmeal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.abim.bestmeal.domain.Logradouro;
import br.com.abim.bestmeal.service.LogradouroService;
import br.com.abim.bestmeal.web.rest.errors.BadRequestAlertException;
import br.com.abim.bestmeal.web.rest.util.HeaderUtil;
import br.com.abim.bestmeal.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Logradouro.
 */
@RestController
@RequestMapping("/api")
public class LogradouroResource {

    private final Logger log = LoggerFactory.getLogger(LogradouroResource.class);

    private static final String ENTITY_NAME = "logradouro";

    private LogradouroService logradouroService;

    public LogradouroResource(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    /**
     * POST  /logradouros : Create a new logradouro.
     *
     * @param logradouro the logradouro to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logradouro, or with status 400 (Bad Request) if the logradouro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/logradouros")
    @Timed
    public ResponseEntity<Logradouro> createLogradouro(@Valid @RequestBody Logradouro logradouro) throws URISyntaxException {
        log.debug("REST request to save Logradouro : {}", logradouro);
        if (logradouro.getId() != null) {
            throw new BadRequestAlertException("A new logradouro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Logradouro result = logradouroService.save(logradouro);
        return ResponseEntity.created(new URI("/api/logradouros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /logradouros : Updates an existing logradouro.
     *
     * @param logradouro the logradouro to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logradouro,
     * or with status 400 (Bad Request) if the logradouro is not valid,
     * or with status 500 (Internal Server Error) if the logradouro couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logradouros")
    @Timed
    public ResponseEntity<Logradouro> updateLogradouro(@Valid @RequestBody Logradouro logradouro) throws URISyntaxException {
        log.debug("REST request to update Logradouro : {}", logradouro);
        if (logradouro.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Logradouro result = logradouroService.save(logradouro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logradouro.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logradouros : get all the logradouros.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of logradouros in body
     */
    @GetMapping("/logradouros")
    @Timed
    public ResponseEntity<List<Logradouro>> getAllLogradouros(Pageable pageable) {
        log.debug("REST request to get a page of Logradouros");
        Page<Logradouro> page = logradouroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/logradouros");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /logradouros/:id : get the "id" logradouro.
     *
     * @param id the id of the logradouro to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logradouro, or with status 404 (Not Found)
     */
    @GetMapping("/logradouros/{id}")
    @Timed
    public ResponseEntity<Logradouro> getLogradouro(@PathVariable Long id) {
        log.debug("REST request to get Logradouro : {}", id);
        Optional<Logradouro> logradouro = logradouroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logradouro);
    }

    /**
     * DELETE  /logradouros/:id : delete the "id" logradouro.
     *
     * @param id the id of the logradouro to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logradouros/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogradouro(@PathVariable Long id) {
        log.debug("REST request to delete Logradouro : {}", id);
        logradouroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
