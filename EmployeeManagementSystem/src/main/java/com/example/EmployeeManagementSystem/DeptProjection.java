package com.example.EmployeeManagementSystem;
import org.springframework.beans.factory.annotation.Value;
public interface DeptProjection {
	@Value("#{target.name}")
    String getName();
}
