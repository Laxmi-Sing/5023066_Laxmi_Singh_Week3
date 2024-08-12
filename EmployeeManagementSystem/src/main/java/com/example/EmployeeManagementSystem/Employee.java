package com.example.EmployeeManagementSystem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@DynamicUpdate
@DynamicInsert
@BatchSize(size = 20)
@NamedQueries({
    @NamedQuery(name = "Employee.findByName",
                query = "SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.findByDepartment",
                query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName")
})
public class Employee extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
//    @Type(type = "org.hibernate.type.StringClobType") 
//    private String description;
    
    @Lob
    private String description;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

