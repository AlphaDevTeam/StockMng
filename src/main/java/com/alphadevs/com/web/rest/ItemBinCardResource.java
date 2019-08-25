package com.alphadevs.com.web.rest;

import com.alphadevs.com.domain.ItemBinCard;
import com.alphadevs.com.service.ItemBinCardService;
import com.alphadevs.com.web.rest.errors.BadRequestAlertException;
import com.alphadevs.com.service.dto.ItemBinCardCriteria;
import com.alphadevs.com.service.ItemBinCardQueryService;

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
 * REST controller for managing {@link com.alphadevs.com.domain.ItemBinCard}.
 */
@RestController
@RequestMapping("/api")
public class ItemBinCardResource {

    private final Logger log = LoggerFactory.getLogger(ItemBinCardResource.class);

    private static final String ENTITY_NAME = "itemBinCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemBinCardService itemBinCardService;

    private final ItemBinCardQueryService itemBinCardQueryService;

    public ItemBinCardResource(ItemBinCardService itemBinCardService, ItemBinCardQueryService itemBinCardQueryService) {
        this.itemBinCardService = itemBinCardService;
        this.itemBinCardQueryService = itemBinCardQueryService;
    }

    /**
     * {@code POST  /item-bin-cards} : Create a new itemBinCard.
     *
     * @param itemBinCard the itemBinCard to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemBinCard, or with status {@code 400 (Bad Request)} if the itemBinCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-bin-cards")
    public ResponseEntity<ItemBinCard> createItemBinCard(@Valid @RequestBody ItemBinCard itemBinCard) throws URISyntaxException {
        log.debug("REST request to save ItemBinCard : {}", itemBinCard);
        if (itemBinCard.getId() != null) {
            throw new BadRequestAlertException("A new itemBinCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemBinCard result = itemBinCardService.save(itemBinCard);
        return ResponseEntity.created(new URI("/api/item-bin-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-bin-cards} : Updates an existing itemBinCard.
     *
     * @param itemBinCard the itemBinCard to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemBinCard,
     * or with status {@code 400 (Bad Request)} if the itemBinCard is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemBinCard couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-bin-cards")
    public ResponseEntity<ItemBinCard> updateItemBinCard(@Valid @RequestBody ItemBinCard itemBinCard) throws URISyntaxException {
        log.debug("REST request to update ItemBinCard : {}", itemBinCard);
        if (itemBinCard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemBinCard result = itemBinCardService.save(itemBinCard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemBinCard.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-bin-cards} : get all the itemBinCards.
     *

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemBinCards in body.
     */
    @GetMapping("/item-bin-cards")
    public ResponseEntity<List<ItemBinCard>> getAllItemBinCards(ItemBinCardCriteria criteria) {
        log.debug("REST request to get ItemBinCards by criteria: {}", criteria);
        List<ItemBinCard> entityList = itemBinCardQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * {@code GET  /item-bin-cards/count} : count all the itemBinCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/item-bin-cards/count")
    public ResponseEntity<Long> countItemBinCards(ItemBinCardCriteria criteria) {
        log.debug("REST request to count ItemBinCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(itemBinCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /item-bin-cards/:id} : get the "id" itemBinCard.
     *
     * @param id the id of the itemBinCard to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemBinCard, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-bin-cards/{id}")
    public ResponseEntity<ItemBinCard> getItemBinCard(@PathVariable Long id) {
        log.debug("REST request to get ItemBinCard : {}", id);
        Optional<ItemBinCard> itemBinCard = itemBinCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemBinCard);
    }

    /**
     * {@code DELETE  /item-bin-cards/:id} : delete the "id" itemBinCard.
     *
     * @param id the id of the itemBinCard to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-bin-cards/{id}")
    public ResponseEntity<Void> deleteItemBinCard(@PathVariable Long id) {
        log.debug("REST request to delete ItemBinCard : {}", id);
        itemBinCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
