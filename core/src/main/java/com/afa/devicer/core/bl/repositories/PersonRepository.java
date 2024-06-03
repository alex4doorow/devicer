package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.SEPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<SEPerson, Long> {
}
