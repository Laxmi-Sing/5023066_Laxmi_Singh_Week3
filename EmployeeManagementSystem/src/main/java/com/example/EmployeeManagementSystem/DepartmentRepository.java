package com.example.EmployeeManagementSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName1(String name);
    List<DeptProjection> findByName(String name);
    @Query("SELECT new com.example.dto.Department2(d.name) FROM Department d WHERE d.id = :id")
    Department2 findDepartment2ById(Long id);
}