package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.SEAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<SEAddress, Long> {
}
