package com.example.EmployeeManagementSystem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    List<Employee> findByName(String name);

//    Employee findByEmail(String email);

//    List<Employee> findByDepartment_Name(String departmentName);

    List<Employee> findByNameStartingWith(String prefix);
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    @Query(value = "SELECT * FROM Employee e WHERE e.email LIKE %:domain", nativeQuery = true)
    List<Employee> findEmployeesByEmailDomain(@Param("domain") String domain);
   
    List<Employee> findByName(@Param("name") String name);

    List<Employee> findByDepartment(@Param("departmentName") String departmentName);
    Page<Employee> findByNameContaining(String name, Pageable pageable);
    //6 
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    Page<Employee> findByNameWithSorting(String name, Pageable pageable);

    List<EmpProjection> findByDepartmentName(String departmentName);
    
    @Query("SELECT new com.example.dto.Employee2(e.name, e.email) FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee2> findEmployee2ByDepartmentName(String departmentName);
}

