package se.fu.chapter12demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.fu.chapter12demo.pojos.Employee;
import se.fu.chapter12demo.services.IEmployeeService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IEmployeeService employeeService;

    @Test
    void getEmployeesReturnsPagedEmployees() throws Exception {
        Employee employee = new Employee(1, "Nguyen Van An", "Developer", new BigDecimal("1500"));
        when(employeeService.getEmployees(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(employee)));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].empId").value(1));
    }

    @Test
    void createReturnsCreatedEmployee() throws Exception {
        Employee employee = new Employee(9, "Vo Thanh Lam", "Scrum Master", new BigDecimal("2100"));
        when(employeeService.create(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "empId": 9,
                                  "name": "Vo Thanh Lam",
                                  "designation": "Scrum Master",
                                  "salary": 2100
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Vo Thanh Lam"));
    }

    @Test
    void deleteReturnsNotFoundWhenEmployeeDoesNotExist() throws Exception {
        when(employeeService.delete(eq(99))).thenReturn(null);

        mockMvc.perform(delete("/api/employees/99"))
                .andExpect(status().isNotFound());
    }
}
