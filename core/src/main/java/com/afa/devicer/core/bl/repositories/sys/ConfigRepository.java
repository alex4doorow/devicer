package com.afa.devicer.core.bl.repositories.sys;

import com.afa.devicer.core.bl.entities.sys.SEConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<SEConfig, Long> {

    List<SEConfig> findByRecStatus(Character recStatus);

    Optional<SEConfig> findByCodeAndRecStatus(String code, Character recStatus);

}