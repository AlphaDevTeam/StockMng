package com.alphadevs.com.repository;

import com.alphadevs.com.domain.PurchaseAccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PurchaseAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseAccountRepository extends JpaRepository<PurchaseAccount, Long>, JpaSpecificationExecutor<PurchaseAccount> {

}
