package com.example.EmployeeManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
//        return employeeRepository.findById(id).map(employee -> {
//            employee.setName(employeeDetails.getName());
//            employee.setEmail(employeeDetails.getEmail());
//            employee.setDepartment(employeeDetails.getDepartment());
//            Employee updatedEmployee = employeeRepository.save(employee);
//            return ResponseEntity.ok(updatedEmployee);
//        }).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Autowired
    private EmployeeService employeeService;
    //6
    @GetMapping("/search")
    public Page<Employee> searchEmployees(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        return employeeService.getEmployeesByName(name, page, size, sortBy);
    }
    
}

