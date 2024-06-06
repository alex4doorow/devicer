package com.afa.devicer.core.bl.repositories.dictionaries;

import com.afa.devicer.core.bl.entities.dictionaries.SEOrderEmailStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEmailStatusTypeRepository extends JpaRepository<SEOrderEmailStatusType, Long> {

}
