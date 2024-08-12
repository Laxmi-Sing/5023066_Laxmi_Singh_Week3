package com.example.EmployeeManagementSystem;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.primary.repository",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceRepositoriesConfig {
}
