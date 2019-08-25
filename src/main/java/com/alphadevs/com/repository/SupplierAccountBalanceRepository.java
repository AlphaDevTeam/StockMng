package com.alphadevs.com.repository;

import com.alphadevs.com.domain.SupplierAccountBalance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SupplierAccountBalance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplierAccountBalanceRepository extends JpaRepository<SupplierAccountBalance, Long>, JpaSpecificationExecutor<SupplierAccountBalance> {

}
