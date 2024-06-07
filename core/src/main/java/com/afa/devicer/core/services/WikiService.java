package com.afa.devicer.core.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class WikiService {

    private ConfigService config;

    @Autowired
    public void setConfig(ConfigService config) {
        this.config = config;
    }
}
