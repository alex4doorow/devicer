package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.SECustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<SECustomer, Long> {
}
