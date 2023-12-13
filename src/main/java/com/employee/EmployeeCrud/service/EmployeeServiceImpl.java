package com.employee.EmployeeCrud.service;

import com.employee.EmployeeCrud.entity.Employee;
import com.employee.EmployeeCrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getHierarchy() {
        List<Employee> allEmployees = employeeRepository.findAll();
        Map<Long, Employee> employeeMap = new HashMap<>();

        for (Employee employee : allEmployees) {
            employeeMap.put(employee.getId(), employee);
        }

        List<Employee> hierarchy = new ArrayList<>();

        for (Employee employee : allEmployees) {
            if (employee.getManager() == null) {
                hierarchy.add(employee);
            } else {
                Employee manager = employeeMap.get(employee.getManager().getId());
                if (manager != null) {
                    manager.getSubordinates().add(employee);
                }
            }
        }

        return hierarchy;
    }


    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()){
            employee = optional.get();
        } else{
            throw new RuntimeException( "Employee with employee number: " +id+ " Not found");
        }
        return employee;
    }

    @Override
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.searchEmployees(keyword);
    }
    @Override
    public void deleteEmployeeById(Long employeeNumber) {
        this.employeeRepository.deleteById(employeeNumber);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort );
        return this.employeeRepository.findAll(pageable);
    }
}
