package br.com.abim.bestmeal.web.rest;

import br.com.abim.bestmeal.BestMealApp;

import br.com.abim.bestmeal.domain.Nome;
import br.com.abim.bestmeal.repository.NomeRepository;
import br.com.abim.bestmeal.service.NomeService;
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

/**
 * Test class for the NomeResource REST controller.
 *
 * @see NomeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BestMealApp.class)
public class NomeResourceIntTest {

    private static final String DEFAULT_PRIMEIRO_NOME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMEIRO_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_MEIO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_MEIO = "BBBBBBBBBB";

    private static final String DEFAULT_SOBRE_NOME = "AAAAAAAAAA";
    private static final String UPDATED_SOBRE_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_SAUDACAO = "AAAAAAAAAA";
    private static final String UPDATED_SAUDACAO = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    @Autowired
    private NomeRepository nomeRepository;
    
    @Autowired
    private NomeService nomeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNomeMockMvc;

    private Nome nome;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NomeResource nomeResource = new NomeResource(nomeService);
        this.restNomeMockMvc = MockMvcBuilders.standaloneSetup(nomeResource)
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
    public static Nome createEntity(EntityManager em) {
        Nome nome = new Nome()
            .primeiroNome(DEFAULT_PRIMEIRO_NOME)
            .nomeMeio(DEFAULT_NOME_MEIO)
            .sobreNome(DEFAULT_SOBRE_NOME)
            .saudacao(DEFAULT_SAUDACAO)
            .titulo(DEFAULT_TITULO);
        return nome;
    }

    @Before
    public void initTest() {
        nome = createEntity(em);
    }

    @Test
    @Transactional
    public void createNome() throws Exception {
        int databaseSizeBeforeCreate = nomeRepository.findAll().size();

        // Create the Nome
        restNomeMockMvc.perform(post("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nome)))
            .andExpect(status().isCreated());

        // Validate the Nome in the database
        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeCreate + 1);
        Nome testNome = nomeList.get(nomeList.size() - 1);
        assertThat(testNome.getPrimeiroNome()).isEqualTo(DEFAULT_PRIMEIRO_NOME);
        assertThat(testNome.getNomeMeio()).isEqualTo(DEFAULT_NOME_MEIO);
        assertThat(testNome.getSobreNome()).isEqualTo(DEFAULT_SOBRE_NOME);
        assertThat(testNome.getSaudacao()).isEqualTo(DEFAULT_SAUDACAO);
        assertThat(testNome.getTitulo()).isEqualTo(DEFAULT_TITULO);
    }

    @Test
    @Transactional
    public void createNomeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nomeRepository.findAll().size();

        // Create the Nome with an existing ID
        nome.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNomeMockMvc.perform(post("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nome)))
            .andExpect(status().isBadRequest());

        // Validate the Nome in the database
        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPrimeiroNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nomeRepository.findAll().size();
        // set the field null
        nome.setPrimeiroNome(null);

        // Create the Nome, which fails.

        restNomeMockMvc.perform(post("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nome)))
            .andExpect(status().isBadRequest());

        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeMeioIsRequired() throws Exception {
        int databaseSizeBeforeTest = nomeRepository.findAll().size();
        // set the field null
        nome.setNomeMeio(null);

        // Create the Nome, which fails.

        restNomeMockMvc.perform(post("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nome)))
            .andExpect(status().isBadRequest());

        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSobreNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nomeRepository.findAll().size();
        // set the field null
        nome.setSobreNome(null);

        // Create the Nome, which fails.

        restNomeMockMvc.perform(post("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nome)))
            .andExpect(status().isBadRequest());

        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = nomeRepository.findAll().size();
        // set the field null
        nome.setTitulo(null);

        // Create the Nome, which fails.

        restNomeMockMvc.perform(post("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nome)))
            .andExpect(status().isBadRequest());

        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNomes() throws Exception {
        // Initialize the database
        nomeRepository.saveAndFlush(nome);

        // Get all the nomeList
        restNomeMockMvc.perform(get("/api/nomes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nome.getId().intValue())))
            .andExpect(jsonPath("$.[*].primeiroNome").value(hasItem(DEFAULT_PRIMEIRO_NOME.toString())))
            .andExpect(jsonPath("$.[*].nomeMeio").value(hasItem(DEFAULT_NOME_MEIO.toString())))
            .andExpect(jsonPath("$.[*].sobreNome").value(hasItem(DEFAULT_SOBRE_NOME.toString())))
            .andExpect(jsonPath("$.[*].saudacao").value(hasItem(DEFAULT_SAUDACAO.toString())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())));
    }
    
    @Test
    @Transactional
    public void getNome() throws Exception {
        // Initialize the database
        nomeRepository.saveAndFlush(nome);

        // Get the nome
        restNomeMockMvc.perform(get("/api/nomes/{id}", nome.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nome.getId().intValue()))
            .andExpect(jsonPath("$.primeiroNome").value(DEFAULT_PRIMEIRO_NOME.toString()))
            .andExpect(jsonPath("$.nomeMeio").value(DEFAULT_NOME_MEIO.toString()))
            .andExpect(jsonPath("$.sobreNome").value(DEFAULT_SOBRE_NOME.toString()))
            .andExpect(jsonPath("$.saudacao").value(DEFAULT_SAUDACAO.toString()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNome() throws Exception {
        // Get the nome
        restNomeMockMvc.perform(get("/api/nomes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNome() throws Exception {
        // Initialize the database
        nomeService.save(nome);

        int databaseSizeBeforeUpdate = nomeRepository.findAll().size();

        // Update the nome
        Nome updatedNome = nomeRepository.findById(nome.getId()).get();
        // Disconnect from session so that the updates on updatedNome are not directly saved in db
        em.detach(updatedNome);
        updatedNome
            .primeiroNome(UPDATED_PRIMEIRO_NOME)
            .nomeMeio(UPDATED_NOME_MEIO)
            .sobreNome(UPDATED_SOBRE_NOME)
            .saudacao(UPDATED_SAUDACAO)
            .titulo(UPDATED_TITULO);

        restNomeMockMvc.perform(put("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNome)))
            .andExpect(status().isOk());

        // Validate the Nome in the database
        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeUpdate);
        Nome testNome = nomeList.get(nomeList.size() - 1);
        assertThat(testNome.getPrimeiroNome()).isEqualTo(UPDATED_PRIMEIRO_NOME);
        assertThat(testNome.getNomeMeio()).isEqualTo(UPDATED_NOME_MEIO);
        assertThat(testNome.getSobreNome()).isEqualTo(UPDATED_SOBRE_NOME);
        assertThat(testNome.getSaudacao()).isEqualTo(UPDATED_SAUDACAO);
        assertThat(testNome.getTitulo()).isEqualTo(UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void updateNonExistingNome() throws Exception {
        int databaseSizeBeforeUpdate = nomeRepository.findAll().size();

        // Create the Nome

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNomeMockMvc.perform(put("/api/nomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nome)))
            .andExpect(status().isBadRequest());

        // Validate the Nome in the database
        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNome() throws Exception {
        // Initialize the database
        nomeService.save(nome);

        int databaseSizeBeforeDelete = nomeRepository.findAll().size();

        // Get the nome
        restNomeMockMvc.perform(delete("/api/nomes/{id}", nome.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nome> nomeList = nomeRepository.findAll();
        assertThat(nomeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nome.class);
        Nome nome1 = new Nome();
        nome1.setId(1L);
        Nome nome2 = new Nome();
        nome2.setId(nome1.getId());
        assertThat(nome1).isEqualTo(nome2);
        nome2.setId(2L);
        assertThat(nome1).isNotEqualTo(nome2);
        nome1.setId(null);
        assertThat(nome1).isNotEqualTo(nome2);
    }
}
