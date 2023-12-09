package com.employee.EmployeeCrud.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.data.domain.Page;
import com.employee.EmployeeCrud.entity.Employee;
import com.employee.EmployeeCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home(Model model){
        return findPaginated(1, "name", "asc", "model");
    }

    @GetMapping("/employee")
    public String showEmployee(Model model){
    Employee employee = new Employee();
    model.addAttribute("employee", employee);
    return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee")Employee employee){
    employeeService.saveEmployee(employee);
    return "redirect:/";
    }

    @GetMapping("/showUpdate/{id}")
    public String showUpdate(@PathVariable(value = "id") long id, Model model){
    Employee employee = employeeService.getEmployeeById(id);

    model.addAttribute("employee", employee);
    return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, String model) {

        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "index";

    }



}