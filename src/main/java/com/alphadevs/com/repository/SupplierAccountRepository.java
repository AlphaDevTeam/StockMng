package com.alphadevs.com.repository;

import com.alphadevs.com.domain.SupplierAccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SupplierAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplierAccountRepository extends JpaRepository<SupplierAccount, Long>, JpaSpecificationExecutor<SupplierAccount> {

}
