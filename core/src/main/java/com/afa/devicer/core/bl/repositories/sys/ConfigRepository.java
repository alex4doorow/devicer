package com.afa.devicer.core.bl.repositories.sys;

import com.afa.devicer.core.bl.entities.sys.SEConfig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "seConfigCache")
public interface ConfigRepository extends JpaRepository<SEConfig, Long> {

    @Cacheable
    List<SEConfig> findByRecStatus(Character recStatus);

}