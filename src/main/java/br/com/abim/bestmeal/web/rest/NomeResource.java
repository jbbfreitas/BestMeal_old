package br.com.abim.bestmeal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.abim.bestmeal.domain.Nome;
import br.com.abim.bestmeal.service.NomeService;
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
 * REST controller for managing Nome.
 */
@RestController
@RequestMapping("/api")
public class NomeResource {

    private final Logger log = LoggerFactory.getLogger(NomeResource.class);

    private static final String ENTITY_NAME = "nome";

    private NomeService nomeService;

    public NomeResource(NomeService nomeService) {
        this.nomeService = nomeService;
    }

    /**
     * POST  /nomes : Create a new nome.
     *
     * @param nome the nome to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nome, or with status 400 (Bad Request) if the nome has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nomes")
    @Timed
    public ResponseEntity<Nome> createNome(@Valid @RequestBody Nome nome) throws URISyntaxException {
        log.debug("REST request to save Nome : {}", nome);
        if (nome.getId() != null) {
            throw new BadRequestAlertException("A new nome cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Nome result = nomeService.save(nome);
        return ResponseEntity.created(new URI("/api/nomes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nomes : Updates an existing nome.
     *
     * @param nome the nome to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nome,
     * or with status 400 (Bad Request) if the nome is not valid,
     * or with status 500 (Internal Server Error) if the nome couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nomes")
    @Timed
    public ResponseEntity<Nome> updateNome(@Valid @RequestBody Nome nome) throws URISyntaxException {
        log.debug("REST request to update Nome : {}", nome);
        if (nome.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Nome result = nomeService.save(nome);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nome.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nomes : get all the nomes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nomes in body
     */
    @GetMapping("/nomes")
    @Timed
    public ResponseEntity<List<Nome>> getAllNomes(Pageable pageable) {
        log.debug("REST request to get a page of Nomes");
        Page<Nome> page = nomeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nomes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nomes/:id : get the "id" nome.
     *
     * @param id the id of the nome to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nome, or with status 404 (Not Found)
     */
    @GetMapping("/nomes/{id}")
    @Timed
    public ResponseEntity<Nome> getNome(@PathVariable Long id) {
        log.debug("REST request to get Nome : {}", id);
        Optional<Nome> nome = nomeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nome);
    }

    /**
     * DELETE  /nomes/:id : delete the "id" nome.
     *
     * @param id the id of the nome to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nomes/{id}")
    @Timed
    public ResponseEntity<Void> deleteNome(@PathVariable Long id) {
        log.debug("REST request to delete Nome : {}", id);
        nomeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
