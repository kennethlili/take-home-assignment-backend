package com.spatiallaser.backend.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.spatiallaser.backend.repository.reading",
        entityManagerFactoryRef = "readingEntityManagerFactory",
        transactionManagerRef = "readingTransactionManager"
)
class ReadingDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "readingDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.reading")
    public DataSource readingDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "readingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readingEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("readingDataSource") DataSource dataSource) {
        Map<String, String> readOnlyProps = new HashMap<>(jpaProperties.getProperties());
        readOnlyProps.put("hibernate.hbm2ddl.auto", "none"); // Disable DDL
        return builder
                .dataSource(dataSource)
                .packages("com.spatiallaser.backend.entity.reading")
                .properties(readOnlyProps)
                .persistenceUnit("reading")
                .build();
    }

    @Primary
    @Bean(name = "readingTransactionManager")
    public PlatformTransactionManager readingTransactionManager(
            @Qualifier("readingEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
