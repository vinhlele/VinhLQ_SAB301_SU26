package se.fu.chapter12demo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.fu.chapter12demo.pojos.Employee;
import se.fu.chapter12demo.services.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Page<Employee> getEmployees(@PageableDefault(size = 5, sort = "empId") Pageable pageable) {
        return employeeService.getEmployees(pageable);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<Employee> update(@PathVariable int empId, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.update(empId, employee);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<Employee> delete(@PathVariable int empId) {
        Employee deletedEmployee = employeeService.delete(empId);
        if (deletedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedEmployee);
    }
}
