package com.afa.devicer.core;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ComponentScan(basePackages = {"com.afa.devicer.core"})
//@EntityScan("com.afa.devicer.core.bl.entities")
//@EnableJpaRepositories(basePackages = "com.afa.devicer.core.bl.repositories")
@ImportResource({"${app.beans-xml-path}"})
@EnableCaching
public class TestApplicationConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
                "seConfigCache",
                "seProductCache",
                "seProductCategoryCache"
        );
    }
}
