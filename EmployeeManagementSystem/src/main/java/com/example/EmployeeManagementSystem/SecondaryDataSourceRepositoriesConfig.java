package com.example.EmployeeManagementSystem;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.secondary.repository",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceRepositoriesConfig {
}

