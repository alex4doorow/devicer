package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.SEUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SEUser, Long> {

}