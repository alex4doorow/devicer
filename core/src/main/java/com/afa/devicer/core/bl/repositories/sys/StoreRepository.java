package com.afa.devicer.core.bl.repositories.sys;

import com.afa.devicer.core.bl.entities.sys.SEStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<SEStore, Long> {

}