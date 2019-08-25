package com.alphadevs.com.repository;

import com.alphadevs.com.domain.CashBookBalance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CashBookBalance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CashBookBalanceRepository extends JpaRepository<CashBookBalance, Long>, JpaSpecificationExecutor<CashBookBalance> {

}
