package se.fu.chapter12demo.services;

import org.junit.jupiter.api.Test;
import se.fu.chapter12demo.pojos.Employee;
import se.fu.chapter12demo.repositories.EmployeeRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeServiceTests {

    private final EmployeeService employeeService = new EmployeeService(new EmployeeRepository());

    @Test
    void updateExistingEmployeeReturnsUpdatedEmployee() {
        Employee employee = new Employee(1, "Nguyen Van An Updated", "Senior Developer", new BigDecimal("2000"));

        Employee updatedEmployee = employeeService.update(1, employee);

        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getDesignation()).isEqualTo("Senior Developer");
        assertThat(employeeService.getEmployeeById("1").getName()).isEqualTo("Nguyen Van An Updated");
    }

    @Test
    void deleteExistingEmployeeRemovesEmployee() {
        Employee deletedEmployee = employeeService.delete(2);

        assertThat(deletedEmployee).isNotNull();
        assertThat(employeeService.getEmployeeById("2")).isNull();
    }
}
