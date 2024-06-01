package com.afa.devicer.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@ComponentScan(basePackages = {"com.afa.devicer.core"})
//@EntityScan("com.afa.devicer.core.bl.entities")
//@EnableJpaRepositories(basePackages = "com.afa.devicer.core.bl.repositories")
@ImportResource({"${app.beans-xml-path}"})
public class TestApplicationConfig {


}
