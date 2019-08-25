package com.alphadevs.com.web.rest;

import com.alphadevs.com.StockMngApp;
import com.alphadevs.com.domain.TransactionType;
import com.alphadevs.com.repository.TransactionTypeRepository;
import com.alphadevs.com.service.TransactionTypeService;
import com.alphadevs.com.web.rest.errors.ExceptionTranslator;
import com.alphadevs.com.service.dto.TransactionTypeCriteria;
import com.alphadevs.com.service.TransactionTypeQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.alphadevs.com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TransactionTypeResource} REST controller.
 */
@SpringBootTest(classes = StockMngApp.class)
public class TransactionTypeResourceIT {

    private static final String DEFAULT_TRANSACTIONYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTIONYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Autowired
    private TransactionTypeQueryService transactionTypeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTransactionTypeMockMvc;

    private TransactionType transactionType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransactionTypeResource transactionTypeResource = new TransactionTypeResource(transactionTypeService, transactionTypeQueryService);
        this.restTransactionTypeMockMvc = MockMvcBuilders.standaloneSetup(transactionTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionType createEntity(EntityManager em) {
        TransactionType transactionType = new TransactionType()
            .transactionypeCode(DEFAULT_TRANSACTIONYPE_CODE)
            .transactionType(DEFAULT_TRANSACTION_TYPE);
        return transactionType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionType createUpdatedEntity(EntityManager em) {
        TransactionType transactionType = new TransactionType()
            .transactionypeCode(UPDATED_TRANSACTIONYPE_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE);
        return transactionType;
    }

    @BeforeEach
    public void initTest() {
        transactionType = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionType() throws Exception {
        int databaseSizeBeforeCreate = transactionTypeRepository.findAll().size();

        // Create the TransactionType
        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isCreated());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionType testTransactionType = transactionTypeList.get(transactionTypeList.size() - 1);
        assertThat(testTransactionType.getTransactionypeCode()).isEqualTo(DEFAULT_TRANSACTIONYPE_CODE);
        assertThat(testTransactionType.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void createTransactionTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionTypeRepository.findAll().size();

        // Create the TransactionType with an existing ID
        transactionType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTransactionypeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setTransactionypeCode(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setTransactionType(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactionTypes() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList
        restTransactionTypeMockMvc.perform(get("/api/transaction-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionType.getId().intValue())))
            .andExpect(jsonPath("$.[*].transactionypeCode").value(hasItem(DEFAULT_TRANSACTIONYPE_CODE.toString())))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getTransactionType() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get the transactionType
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/{id}", transactionType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transactionType.getId().intValue()))
            .andExpect(jsonPath("$.transactionypeCode").value(DEFAULT_TRANSACTIONYPE_CODE.toString()))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getAllTransactionTypesByTransactionypeCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList where transactionypeCode equals to DEFAULT_TRANSACTIONYPE_CODE
        defaultTransactionTypeShouldBeFound("transactionypeCode.equals=" + DEFAULT_TRANSACTIONYPE_CODE);

        // Get all the transactionTypeList where transactionypeCode equals to UPDATED_TRANSACTIONYPE_CODE
        defaultTransactionTypeShouldNotBeFound("transactionypeCode.equals=" + UPDATED_TRANSACTIONYPE_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionTypesByTransactionypeCodeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList where transactionypeCode in DEFAULT_TRANSACTIONYPE_CODE or UPDATED_TRANSACTIONYPE_CODE
        defaultTransactionTypeShouldBeFound("transactionypeCode.in=" + DEFAULT_TRANSACTIONYPE_CODE + "," + UPDATED_TRANSACTIONYPE_CODE);

        // Get all the transactionTypeList where transactionypeCode equals to UPDATED_TRANSACTIONYPE_CODE
        defaultTransactionTypeShouldNotBeFound("transactionypeCode.in=" + UPDATED_TRANSACTIONYPE_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionTypesByTransactionypeCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList where transactionypeCode is not null
        defaultTransactionTypeShouldBeFound("transactionypeCode.specified=true");

        // Get all the transactionTypeList where transactionypeCode is null
        defaultTransactionTypeShouldNotBeFound("transactionypeCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllTransactionTypesByTransactionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList where transactionType equals to DEFAULT_TRANSACTION_TYPE
        defaultTransactionTypeShouldBeFound("transactionType.equals=" + DEFAULT_TRANSACTION_TYPE);

        // Get all the transactionTypeList where transactionType equals to UPDATED_TRANSACTION_TYPE
        defaultTransactionTypeShouldNotBeFound("transactionType.equals=" + UPDATED_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllTransactionTypesByTransactionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList where transactionType in DEFAULT_TRANSACTION_TYPE or UPDATED_TRANSACTION_TYPE
        defaultTransactionTypeShouldBeFound("transactionType.in=" + DEFAULT_TRANSACTION_TYPE + "," + UPDATED_TRANSACTION_TYPE);

        // Get all the transactionTypeList where transactionType equals to UPDATED_TRANSACTION_TYPE
        defaultTransactionTypeShouldNotBeFound("transactionType.in=" + UPDATED_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllTransactionTypesByTransactionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList where transactionType is not null
        defaultTransactionTypeShouldBeFound("transactionType.specified=true");

        // Get all the transactionTypeList where transactionType is null
        defaultTransactionTypeShouldNotBeFound("transactionType.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTransactionTypeShouldBeFound(String filter) throws Exception {
        restTransactionTypeMockMvc.perform(get("/api/transaction-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionType.getId().intValue())))
            .andExpect(jsonPath("$.[*].transactionypeCode").value(hasItem(DEFAULT_TRANSACTIONYPE_CODE)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE)));

        // Check, that the count call also returns 1
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTransactionTypeShouldNotBeFound(String filter) throws Exception {
        restTransactionTypeMockMvc.perform(get("/api/transaction-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTransactionType() throws Exception {
        // Get the transactionType
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionType() throws Exception {
        // Initialize the database
        transactionTypeService.save(transactionType);

        int databaseSizeBeforeUpdate = transactionTypeRepository.findAll().size();

        // Update the transactionType
        TransactionType updatedTransactionType = transactionTypeRepository.findById(transactionType.getId()).get();
        // Disconnect from session so that the updates on updatedTransactionType are not directly saved in db
        em.detach(updatedTransactionType);
        updatedTransactionType
            .transactionypeCode(UPDATED_TRANSACTIONYPE_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE);

        restTransactionTypeMockMvc.perform(put("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransactionType)))
            .andExpect(status().isOk());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeUpdate);
        TransactionType testTransactionType = transactionTypeList.get(transactionTypeList.size() - 1);
        assertThat(testTransactionType.getTransactionypeCode()).isEqualTo(UPDATED_TRANSACTIONYPE_CODE);
        assertThat(testTransactionType.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionType() throws Exception {
        int databaseSizeBeforeUpdate = transactionTypeRepository.findAll().size();

        // Create the TransactionType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionTypeMockMvc.perform(put("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactionType() throws Exception {
        // Initialize the database
        transactionTypeService.save(transactionType);

        int databaseSizeBeforeDelete = transactionTypeRepository.findAll().size();

        // Delete the transactionType
        restTransactionTypeMockMvc.perform(delete("/api/transaction-types/{id}", transactionType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionType.class);
        TransactionType transactionType1 = new TransactionType();
        transactionType1.setId(1L);
        TransactionType transactionType2 = new TransactionType();
        transactionType2.setId(transactionType1.getId());
        assertThat(transactionType1).isEqualTo(transactionType2);
        transactionType2.setId(2L);
        assertThat(transactionType1).isNotEqualTo(transactionType2);
        transactionType1.setId(null);
        assertThat(transactionType1).isNotEqualTo(transactionType2);
    }
}
