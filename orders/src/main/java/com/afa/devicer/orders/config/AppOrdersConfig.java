package com.afa.devicer.orders.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.afa.devicer.orders",
        "com.afa.devicer.core.services",
        "com.afa.devicer.core.integration"
})
@EntityScan("com.afa.devicer.core.bl.entities")
@EnableJpaRepositories(basePackages = "com.afa.devicer.core.bl.repositories")
public class AppOrdersConfig {


}
