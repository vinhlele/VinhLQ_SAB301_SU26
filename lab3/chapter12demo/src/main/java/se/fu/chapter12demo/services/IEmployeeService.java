package se.fu.chapter12demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.fu.chapter12demo.pojos.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getAllEmployees();

    Page<Employee> getEmployees(Pageable pageable);

    Employee getEmployeeById(String empId);

    Employee create(Employee employee);

    Employee update(int empId, Employee employee);

    Employee delete(int empId);
}
