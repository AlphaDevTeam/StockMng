package com.alphadevs.com.repository;

import com.alphadevs.com.domain.SalesAccountBalance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SalesAccountBalance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesAccountBalanceRepository extends JpaRepository<SalesAccountBalance, Long>, JpaSpecificationExecutor<SalesAccountBalance> {

}
