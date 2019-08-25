package com.alphadevs.com.web.rest;

import com.alphadevs.com.domain.DocumentType;
import com.alphadevs.com.service.DocumentTypeService;
import com.alphadevs.com.web.rest.errors.BadRequestAlertException;
import com.alphadevs.com.service.dto.DocumentTypeCriteria;
import com.alphadevs.com.service.DocumentTypeQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.alphadevs.com.domain.DocumentType}.
 */
@RestController
@RequestMapping("/api")
public class DocumentTypeResource {

    private final Logger log = LoggerFactory.getLogger(DocumentTypeResource.class);

    private static final String ENTITY_NAME = "documentType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentTypeService documentTypeService;

    private final DocumentTypeQueryService documentTypeQueryService;

    public DocumentTypeResource(DocumentTypeService documentTypeService, DocumentTypeQueryService documentTypeQueryService) {
        this.documentTypeService = documentTypeService;
        this.documentTypeQueryService = documentTypeQueryService;
    }

    /**
     * {@code POST  /document-types} : Create a new documentType.
     *
     * @param documentType the documentType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentType, or with status {@code 400 (Bad Request)} if the documentType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/document-types")
    public ResponseEntity<DocumentType> createDocumentType(@Valid @RequestBody DocumentType documentType) throws URISyntaxException {
        log.debug("REST request to save DocumentType : {}", documentType);
        if (documentType.getId() != null) {
            throw new BadRequestAlertException("A new documentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentType result = documentTypeService.save(documentType);
        return ResponseEntity.created(new URI("/api/document-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /document-types} : Updates an existing documentType.
     *
     * @param documentType the documentType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentType,
     * or with status {@code 400 (Bad Request)} if the documentType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/document-types")
    public ResponseEntity<DocumentType> updateDocumentType(@Valid @RequestBody DocumentType documentType) throws URISyntaxException {
        log.debug("REST request to update DocumentType : {}", documentType);
        if (documentType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentType result = documentTypeService.save(documentType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documentType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /document-types} : get all the documentTypes.
     *

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentTypes in body.
     */
    @GetMapping("/document-types")
    public ResponseEntity<List<DocumentType>> getAllDocumentTypes(DocumentTypeCriteria criteria) {
        log.debug("REST request to get DocumentTypes by criteria: {}", criteria);
        List<DocumentType> entityList = documentTypeQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * {@code GET  /document-types/count} : count all the documentTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/document-types/count")
    public ResponseEntity<Long> countDocumentTypes(DocumentTypeCriteria criteria) {
        log.debug("REST request to count DocumentTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(documentTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /document-types/:id} : get the "id" documentType.
     *
     * @param id the id of the documentType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/document-types/{id}")
    public ResponseEntity<DocumentType> getDocumentType(@PathVariable Long id) {
        log.debug("REST request to get DocumentType : {}", id);
        Optional<DocumentType> documentType = documentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentType);
    }

    /**
     * {@code DELETE  /document-types/:id} : delete the "id" documentType.
     *
     * @param id the id of the documentType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/document-types/{id}")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) {
        log.debug("REST request to delete DocumentType : {}", id);
        documentTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
