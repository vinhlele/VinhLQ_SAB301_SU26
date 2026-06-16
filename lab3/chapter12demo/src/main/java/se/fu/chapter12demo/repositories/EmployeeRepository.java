package se.fu.chapter12demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import se.fu.chapter12demo.pojos.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final Map<Integer, Employee> employees = new LinkedHashMap<>();

    public EmployeeRepository() {
        createList();
    }

    @Override
    public List<Employee> createList() {
        employees.clear();
        employees.put(1, new Employee(1, "Nguyen Van An", "Developer", new BigDecimal("1500")));
        employees.put(2, new Employee(2, "Tran Thi Binh", "Tester", new BigDecimal("1200")));
        employees.put(3, new Employee(3, "Le Minh Chau", "Business Analyst", new BigDecimal("1400")));
        employees.put(4, new Employee(4, "Pham Quoc Dung", "Project Manager", new BigDecimal("2500")));
        employees.put(5, new Employee(5, "Hoang Thu Ha", "UI Designer", new BigDecimal("1300")));
        return getAllEmployees();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee getEmployeeById(String empId) {
        return parseId(empId)
                .map(employees::get)
                .orElse(null);
    }

    @Override
    public Employee delete(int id) {
        return employees.remove(id);
    }

    @Override
    public Employee create(Employee employee) {
        employees.put(employee.getEmpId(), employee);
        return employee;
    }

    @Override
    public Employee update(int id, Employee employee) {
        if (!employees.containsKey(id)) {
            return null;
        }
        employee.setEmpId(id);
        employees.put(id, employee);
        return employee;
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        List<Employee> sortedEmployees = getAllEmployees();
        if (sort.isUnsorted()) {
            return sortedEmployees;
        }

        Comparator<Employee> comparator = null;
        for (Sort.Order order : sort) {
            Comparator<Employee> orderComparator = comparatorFor(order.getProperty());
            if (order.isDescending()) {
                orderComparator = orderComparator.reversed();
            }
            comparator = comparator == null ? orderComparator : comparator.thenComparing(orderComparator);
        }
        sortedEmployees.sort(comparator);
        return sortedEmployees;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        List<Employee> sortedEmployees = findAll(pageable.getSort());
        int start = (int) pageable.getOffset();
        if (start >= sortedEmployees.size()) {
            return new PageImpl<>(List.of(), pageable, sortedEmployees.size());
        }
        int end = Math.min(start + pageable.getPageSize(), sortedEmployees.size());
        return new PageImpl<>(sortedEmployees.subList(start, end), pageable, sortedEmployees.size());
    }

    private Optional<Integer> parseId(String empId) {
        try {
            return Optional.of(Integer.parseInt(empId));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    private Comparator<Employee> comparatorFor(String property) {
        return switch (property) {
            case "empId", "id" -> Comparator.comparing(Employee::getEmpId);
            case "name" -> Comparator.comparing(Employee::getName, Comparator.nullsLast(String::compareToIgnoreCase));
            case "designation" -> Comparator.comparing(Employee::getDesignation, Comparator.nullsLast(String::compareToIgnoreCase));
            case "salary" -> Comparator.comparing(Employee::getSalary, Comparator.nullsLast(BigDecimal::compareTo));
            default -> Comparator.comparing(Employee::getEmpId);
        };
    }
}
