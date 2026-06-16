package se.fu.chapter12demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.fu.chapter12demo.pojos.Employee;
import se.fu.chapter12demo.repositories.IEmployeeRepository;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(String empId) {
        return employeeRepository.getEmployeeById(empId);
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.create(employee);
    }

    @Override
    public Employee update(int empId, Employee employee) {
        return employeeRepository.update(empId, employee);
    }

    @Override
    public Employee delete(int empId) {
        return employeeRepository.delete(empId);
    }
}
