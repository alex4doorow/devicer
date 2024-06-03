package com.afa.devicer.core.bl.repositories.sys;

import com.afa.devicer.core.bl.entities.sys.SEUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SEUser, Long> {

}