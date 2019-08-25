package com.alphadevs.com.service;

import com.alphadevs.com.domain.ItemBinCard;
import com.alphadevs.com.domain.Items;
import com.alphadevs.com.domain.Stock;
import com.alphadevs.com.repository.ItemBinCardRepository;
import com.alphadevs.com.repository.ItemsRepository;
import com.alphadevs.com.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Items}.
 */
@Service
@Transactional
public class ItemsService {

    private final Logger log = LoggerFactory.getLogger(ItemsService.class);

    private final ItemsRepository itemsRepository;
    private final StockRepository stockRepository;
    private final ItemBinCardRepository itemBinCardRepository;

    public ItemsService(ItemsRepository itemsRepository, StockRepository stockRepository, ItemBinCardRepository itemBinCardRepository) {
        this.itemsRepository = itemsRepository;
        this.stockRepository = stockRepository;
        this.itemBinCardRepository = itemBinCardRepository;
    }

    /**
     * Save a items.
     *
     * @param items the entity to save.
     * @return the persisted entity.
     */
    public Items save(Items items) {
        log.debug("Request to save Items : {}", items);
        Stock stock = new Stock();
        stock.setItem(items);
        stock.setCompany(items.getLocation().getCompany());
        stock.setLocation(items.getLocation());
        stock.setStockQty(0.0);
        stockRepository.save(stock);

        ItemBinCard itemBinCard = new ItemBinCard();
        itemBinCard.setItem(items);
        itemBinCard.setLocation(items.getLocation());
        itemBinCard.setTransactionBalance(0.0);
        itemBinCard.setTransactionDate(items.getModifiedStockDate());
        itemBinCard.setTransactionQty(0.0);
        itemBinCard.setTransactionDescription("Item Added to Masterdata");
        itemBinCardRepository.save(itemBinCard);

        return itemsRepository.save(items);
    }

    /**
     * Get all the items.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Items> findAll() {
        log.debug("Request to get all Items");
        return itemsRepository.findAll();
    }


    /**
     * Get one items by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Items> findOne(Long id) {
        log.debug("Request to get Items : {}", id);
        return itemsRepository.findById(id);
    }

    /**
     * Delete the items by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Items : {}", id);
        itemsRepository.deleteById(id);
    }
}
