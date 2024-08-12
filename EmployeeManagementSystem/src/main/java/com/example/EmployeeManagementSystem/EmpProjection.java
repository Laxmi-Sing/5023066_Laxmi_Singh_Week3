package com.example.EmployeeManagementSystem;
import org.springframework.beans.factory.annotation.Value;
public interface EmpProjection {
	@Value("#{target.name}")
    String getName();

    @Value("#{target.email}")
    String getEmail();
}
