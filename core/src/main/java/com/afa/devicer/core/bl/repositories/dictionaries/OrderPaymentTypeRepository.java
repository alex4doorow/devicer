package com.afa.devicer.core.bl.repositories.dictionaries;

import com.afa.devicer.core.bl.entities.dictionaries.SEOrderPaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentTypeRepository extends JpaRepository<SEOrderPaymentType, Long> {

}
