package br.com.abim.bestmeal.web.rest;

import br.com.abim.bestmeal.BestMealApp;

import br.com.abim.bestmeal.domain.Logradouro;
import br.com.abim.bestmeal.repository.LogradouroRepository;
import br.com.abim.bestmeal.service.LogradouroService;
import br.com.abim.bestmeal.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.abim.bestmeal.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.abim.bestmeal.domain.enumeration.TipoLogradouro;
/**
 * Test class for the LogradouroResource REST controller.
 *
 * @see LogradouroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BestMealApp.class)
public class LogradouroResourceIntTest {

    private static final TipoLogradouro DEFAULT_TIPO = TipoLogradouro.RUA;
    private static final TipoLogradouro UPDATED_TIPO = TipoLogradouro.AVENIDA;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private LogradouroRepository logradouroRepository;
    
    @Autowired
    private LogradouroService logradouroService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLogradouroMockMvc;

    private Logradouro logradouro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LogradouroResource logradouroResource = new LogradouroResource(logradouroService);
        this.restLogradouroMockMvc = MockMvcBuilders.standaloneSetup(logradouroResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Logradouro createEntity(EntityManager em) {
        Logradouro logradouro = new Logradouro()
            .tipo(DEFAULT_TIPO)
            .nome(DEFAULT_NOME);
        return logradouro;
    }

    @Before
    public void initTest() {
        logradouro = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogradouro() throws Exception {
        int databaseSizeBeforeCreate = logradouroRepository.findAll().size();

        // Create the Logradouro
        restLogradouroMockMvc.perform(post("/api/logradouros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logradouro)))
            .andExpect(status().isCreated());

        // Validate the Logradouro in the database
        List<Logradouro> logradouroList = logradouroRepository.findAll();
        assertThat(logradouroList).hasSize(databaseSizeBeforeCreate + 1);
        Logradouro testLogradouro = logradouroList.get(logradouroList.size() - 1);
        assertThat(testLogradouro.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testLogradouro.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createLogradouroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logradouroRepository.findAll().size();

        // Create the Logradouro with an existing ID
        logradouro.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogradouroMockMvc.perform(post("/api/logradouros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logradouro)))
            .andExpect(status().isBadRequest());

        // Validate the Logradouro in the database
        List<Logradouro> logradouroList = logradouroRepository.findAll();
        assertThat(logradouroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = logradouroRepository.findAll().size();
        // set the field null
        logradouro.setNome(null);

        // Create the Logradouro, which fails.

        restLogradouroMockMvc.perform(post("/api/logradouros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logradouro)))
            .andExpect(status().isBadRequest());

        List<Logradouro> logradouroList = logradouroRepository.findAll();
        assertThat(logradouroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLogradouros() throws Exception {
        // Initialize the database
        logradouroRepository.saveAndFlush(logradouro);

        // Get all the logradouroList
        restLogradouroMockMvc.perform(get("/api/logradouros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logradouro.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getLogradouro() throws Exception {
        // Initialize the database
        logradouroRepository.saveAndFlush(logradouro);

        // Get the logradouro
        restLogradouroMockMvc.perform(get("/api/logradouros/{id}", logradouro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(logradouro.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLogradouro() throws Exception {
        // Get the logradouro
        restLogradouroMockMvc.perform(get("/api/logradouros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogradouro() throws Exception {
        // Initialize the database
        logradouroService.save(logradouro);

        int databaseSizeBeforeUpdate = logradouroRepository.findAll().size();

        // Update the logradouro
        Logradouro updatedLogradouro = logradouroRepository.findById(logradouro.getId()).get();
        // Disconnect from session so that the updates on updatedLogradouro are not directly saved in db
        em.detach(updatedLogradouro);
        updatedLogradouro
            .tipo(UPDATED_TIPO)
            .nome(UPDATED_NOME);

        restLogradouroMockMvc.perform(put("/api/logradouros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLogradouro)))
            .andExpect(status().isOk());

        // Validate the Logradouro in the database
        List<Logradouro> logradouroList = logradouroRepository.findAll();
        assertThat(logradouroList).hasSize(databaseSizeBeforeUpdate);
        Logradouro testLogradouro = logradouroList.get(logradouroList.size() - 1);
        assertThat(testLogradouro.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testLogradouro.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingLogradouro() throws Exception {
        int databaseSizeBeforeUpdate = logradouroRepository.findAll().size();

        // Create the Logradouro

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogradouroMockMvc.perform(put("/api/logradouros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logradouro)))
            .andExpect(status().isBadRequest());

        // Validate the Logradouro in the database
        List<Logradouro> logradouroList = logradouroRepository.findAll();
        assertThat(logradouroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLogradouro() throws Exception {
        // Initialize the database
        logradouroService.save(logradouro);

        int databaseSizeBeforeDelete = logradouroRepository.findAll().size();

        // Get the logradouro
        restLogradouroMockMvc.perform(delete("/api/logradouros/{id}", logradouro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Logradouro> logradouroList = logradouroRepository.findAll();
        assertThat(logradouroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Logradouro.class);
        Logradouro logradouro1 = new Logradouro();
        logradouro1.setId(1L);
        Logradouro logradouro2 = new Logradouro();
        logradouro2.setId(logradouro1.getId());
        assertThat(logradouro1).isEqualTo(logradouro2);
        logradouro2.setId(2L);
        assertThat(logradouro1).isNotEqualTo(logradouro2);
        logradouro1.setId(null);
        assertThat(logradouro1).isNotEqualTo(logradouro2);
    }
}
