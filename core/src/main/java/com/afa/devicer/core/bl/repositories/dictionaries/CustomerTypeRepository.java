package com.afa.devicer.core.bl.repositories.dictionaries;

import com.afa.devicer.core.bl.entities.dictionaries.SECustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<SECustomerType, Long> {

}
