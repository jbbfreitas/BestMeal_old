package br.com.abim.bestmeal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.abim.bestmeal.domain.Municipio;
import br.com.abim.bestmeal.service.MunicipioService;
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
 * REST controller for managing Municipio.
 */
@RestController
@RequestMapping("/api")
public class MunicipioResource {

    private final Logger log = LoggerFactory.getLogger(MunicipioResource.class);

    private static final String ENTITY_NAME = "municipio";

    private MunicipioService municipioService;

    public MunicipioResource(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    /**
     * POST  /municipios : Create a new municipio.
     *
     * @param municipio the municipio to create
     * @return the ResponseEntity with status 201 (Created) and with body the new municipio, or with status 400 (Bad Request) if the municipio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/municipios")
    @Timed
    public ResponseEntity<Municipio> createMunicipio(@Valid @RequestBody Municipio municipio) throws URISyntaxException {
        log.debug("REST request to save Municipio : {}", municipio);
        if (municipio.getId() != null) {
            throw new BadRequestAlertException("A new municipio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Municipio result = municipioService.save(municipio);
        return ResponseEntity.created(new URI("/api/municipios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /municipios : Updates an existing municipio.
     *
     * @param municipio the municipio to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated municipio,
     * or with status 400 (Bad Request) if the municipio is not valid,
     * or with status 500 (Internal Server Error) if the municipio couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/municipios")
    @Timed
    public ResponseEntity<Municipio> updateMunicipio(@Valid @RequestBody Municipio municipio) throws URISyntaxException {
        log.debug("REST request to update Municipio : {}", municipio);
        if (municipio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Municipio result = municipioService.save(municipio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, municipio.getId().toString()))
            .body(result);
    }

    /**
     * GET  /municipios : get all the municipios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of municipios in body
     */
    @GetMapping("/municipios")
    @Timed
    public ResponseEntity<List<Municipio>> getAllMunicipios(Pageable pageable) {
        log.debug("REST request to get a page of Municipios");
        Page<Municipio> page = municipioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/municipios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /municipios/:id : get the "id" municipio.
     *
     * @param id the id of the municipio to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the municipio, or with status 404 (Not Found)
     */
    @GetMapping("/municipios/{id}")
    @Timed
    public ResponseEntity<Municipio> getMunicipio(@PathVariable Long id) {
        log.debug("REST request to get Municipio : {}", id);
        Optional<Municipio> municipio = municipioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(municipio);
    }

    /**
     * DELETE  /municipios/:id : delete the "id" municipio.
     *
     * @param id the id of the municipio to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/municipios/{id}")
    @Timed
    public ResponseEntity<Void> deleteMunicipio(@PathVariable Long id) {
        log.debug("REST request to delete Municipio : {}", id);
        municipioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
