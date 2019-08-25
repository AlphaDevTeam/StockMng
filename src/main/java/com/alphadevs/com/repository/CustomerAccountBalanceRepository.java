package com.alphadevs.com.repository;

import com.alphadevs.com.domain.CustomerAccountBalance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomerAccountBalance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerAccountBalanceRepository extends JpaRepository<CustomerAccountBalance, Long>, JpaSpecificationExecutor<CustomerAccountBalance> {

}
