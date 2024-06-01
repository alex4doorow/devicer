package com.afa.devicer.statistic.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.afa.devicer.statistic",
        "com.afa.devicer.core.services"
})
@EntityScan("com.afa.devicer.core.bl.entities")
@EnableJpaRepositories(basePackages = "com.afa.devicer.core.bl.repositories")
public class AppStatisticConfig {


}
