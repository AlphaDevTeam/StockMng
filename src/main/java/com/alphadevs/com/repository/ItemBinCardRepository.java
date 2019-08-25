package com.alphadevs.com.repository;

import com.alphadevs.com.domain.ItemBinCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemBinCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemBinCardRepository extends JpaRepository<ItemBinCard, Long>, JpaSpecificationExecutor<ItemBinCard> {

}
