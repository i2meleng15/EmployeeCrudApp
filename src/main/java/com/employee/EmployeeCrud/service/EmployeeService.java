package com.employee.EmployeeCrud.service;

import com.employee.EmployeeCrud.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);

    List<Employee> getHierarchy();
    List<Employee> searchEmployees(String keyword);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
