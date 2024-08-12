package com.example.EmployeeManagementSystem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

@Configuration
public class SecondaryDataConfiguration {

    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.secondary.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.secondary.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.secondary.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.secondary.password"));
        return dataSource;
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.example.secondary");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return emf;
    }

    @Bean(name = "secondaryTransactionManager")
    public JpaTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

