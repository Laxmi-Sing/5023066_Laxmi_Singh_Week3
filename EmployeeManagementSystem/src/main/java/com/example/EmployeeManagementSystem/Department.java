package com.example.EmployeeManagementSystem;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
public class Department extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

