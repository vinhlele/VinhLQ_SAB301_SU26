package se.fu.chapter12demo.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import se.fu.chapter12demo.pojos.Employee;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryTests {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    @Test
    void getEmployeeByIdReturnsEmployee() {
        Employee employee = employeeRepository.getEmployeeById("1");

        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo("Nguyen Van An");
    }

    @Test
    void createAddsEmployeeToMemoryStore() {
        Employee employee = new Employee(10, "Do Van Khoa", "DevOps Engineer", new BigDecimal("1800"));

        employeeRepository.create(employee);

        assertThat(employeeRepository.getEmployeeById("10")).isEqualTo(employee);
    }

    @Test
    void findAllSupportsPagingAndSorting() {
        Page<Employee> page = employeeRepository.findAll(PageRequest.of(0, 2, Sort.by("salary").descending()));

        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent().getFirst().getEmpId()).isEqualTo(4);
    }
}
