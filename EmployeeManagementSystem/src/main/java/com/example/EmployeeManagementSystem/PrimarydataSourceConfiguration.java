package com.example.EmployeeManagementSystem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManager;

import javax.sql.DataSource;

@Configuration
public class PrimarydataSourceConfiguration {
	@Bean(name = "primaryDataSource")
    @Primary
    public DataSource primaryDataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.primary.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.primary.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.primary.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.primary.password"));
        return dataSource;
    }

    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.example.primary"); // Replace with your package
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return emf;
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public JpaTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManager entityManagerFactory) {
        return new JpaTransactionManager();
    }
}
