package br.com.abim.bestmeal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.abim.bestmeal.domain.CartaoRecompensa;
import br.com.abim.bestmeal.service.CartaoRecompensaService;
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
 * REST controller for managing CartaoRecompensa.
 */
@RestController
@RequestMapping("/api")
public class CartaoRecompensaResource {

    private final Logger log = LoggerFactory.getLogger(CartaoRecompensaResource.class);

    private static final String ENTITY_NAME = "cartaoRecompensa";

    private CartaoRecompensaService cartaoRecompensaService;

    public CartaoRecompensaResource(CartaoRecompensaService cartaoRecompensaService) {
        this.cartaoRecompensaService = cartaoRecompensaService;
    }

    /**
     * POST  /cartao-recompensas : Create a new cartaoRecompensa.
     *
     * @param cartaoRecompensa the cartaoRecompensa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cartaoRecompensa, or with status 400 (Bad Request) if the cartaoRecompensa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cartao-recompensas")
    @Timed
    public ResponseEntity<CartaoRecompensa> createCartaoRecompensa(@Valid @RequestBody CartaoRecompensa cartaoRecompensa) throws URISyntaxException {
        log.debug("REST request to save CartaoRecompensa : {}", cartaoRecompensa);
        if (cartaoRecompensa.getId() != null) {
            throw new BadRequestAlertException("A new cartaoRecompensa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CartaoRecompensa result = cartaoRecompensaService.save(cartaoRecompensa);
        return ResponseEntity.created(new URI("/api/cartao-recompensas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cartao-recompensas : Updates an existing cartaoRecompensa.
     *
     * @param cartaoRecompensa the cartaoRecompensa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cartaoRecompensa,
     * or with status 400 (Bad Request) if the cartaoRecompensa is not valid,
     * or with status 500 (Internal Server Error) if the cartaoRecompensa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cartao-recompensas")
    @Timed
    public ResponseEntity<CartaoRecompensa> updateCartaoRecompensa(@Valid @RequestBody CartaoRecompensa cartaoRecompensa) throws URISyntaxException {
        log.debug("REST request to update CartaoRecompensa : {}", cartaoRecompensa);
        if (cartaoRecompensa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CartaoRecompensa result = cartaoRecompensaService.save(cartaoRecompensa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cartaoRecompensa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cartao-recompensas : get all the cartaoRecompensas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cartaoRecompensas in body
     */
    @GetMapping("/cartao-recompensas")
    @Timed
    public ResponseEntity<List<CartaoRecompensa>> getAllCartaoRecompensas(Pageable pageable) {
        log.debug("REST request to get a page of CartaoRecompensas");
        Page<CartaoRecompensa> page = cartaoRecompensaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cartao-recompensas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cartao-recompensas/:id : get the "id" cartaoRecompensa.
     *
     * @param id the id of the cartaoRecompensa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cartaoRecompensa, or with status 404 (Not Found)
     */
    @GetMapping("/cartao-recompensas/{id}")
    @Timed
    public ResponseEntity<CartaoRecompensa> getCartaoRecompensa(@PathVariable Long id) {
        log.debug("REST request to get CartaoRecompensa : {}", id);
        Optional<CartaoRecompensa> cartaoRecompensa = cartaoRecompensaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cartaoRecompensa);
    }

    /**
     * DELETE  /cartao-recompensas/:id : delete the "id" cartaoRecompensa.
     *
     * @param id the id of the cartaoRecompensa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cartao-recompensas/{id}")
    @Timed
    public ResponseEntity<Void> deleteCartaoRecompensa(@PathVariable Long id) {
        log.debug("REST request to delete CartaoRecompensa : {}", id);
        cartaoRecompensaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
