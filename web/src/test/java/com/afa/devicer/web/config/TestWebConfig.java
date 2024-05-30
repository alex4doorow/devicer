package com.afa.devicer.web.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

//@Configuration
//@ComponentScan(basePackages = {"com.afa.devicer"})
//@EntityScan("com.afa.devicer.core.bl.entities")
//@EnableJpaRepositories(basePackages = "com.afa.devicer.core.bl.repositories")
public class TestWebConfig {

//    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) // Используйте встроенную базу данных H2 для тестов
                .build();
    }

//    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        // Установите пакет, содержащий ваши сущности
        em.setPackagesToScan("com.afa.devicer.core.model");
        // Установите поставщика JPA (в данном случае используется Hibernate)
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.afterPropertiesSet(); // не забудьте вызвать этот метод
        return em.getObject();
    }

    //@Bean
    public TestEntityManager testEntityManager() {
        return new TestEntityManager(entityManagerFactory());
    }
}
