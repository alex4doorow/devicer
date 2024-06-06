package com.afa.devicer.core.services;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.sys.SEConfig;
import com.afa.devicer.core.bl.repositories.sys.ConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

//    @Cacheable(value = "seConfigCache", key = "#code")
    @Cacheable("seConfigCache")
    public List<SEConfig> getAll() {
        return configRepository.findByRecStatus(BaseEntity.ACTIVE);
    }

    @Cacheable(value = "seConfigCache", key = "#code")
    public SEConfig getValueByCode(String code) {
        log.info("getValueByCode code: {}", code);
        return configRepository.findByCodeAndRecStatus(code, BaseEntity.ACTIVE).orElse(null);
    }
}
