package se.fu.chapter12demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.fu.chapter12demo.pojos.Employee;

import java.util.List;

public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    List<Employee> createList();

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String empId);

    Employee delete(int id);

    Employee create(Employee employee);

    Employee update(int id, Employee employee);

    @Override
    List<Employee> findAll(Sort sort);

    @Override
    Page<Employee> findAll(Pageable pageable);
}
