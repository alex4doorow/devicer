package com.afa.devicer.core.bl.repositories.sys;

import com.afa.devicer.core.bl.entities.sys.SEUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SEUser, Long> {

    Optional<SEUser> findByUsername(String userName);
}