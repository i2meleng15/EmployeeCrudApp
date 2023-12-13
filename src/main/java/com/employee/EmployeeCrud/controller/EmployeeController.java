package com.employee.EmployeeCrud.controller;

import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import com.employee.EmployeeCrud.entity.Employee;
import com.employee.EmployeeCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ModelAndView home(Model model) {
        return findPaginated(1, "name", "asc");
    }

    @GetMapping("/employee")
    public ModelAndView showEmployee() {
        ModelAndView modelAndView = new ModelAndView("new_employee");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showUpdate/{id}")
    public ModelAndView showUpdate(@PathVariable(value = "id") long id) {
        ModelAndView modelAndView = new ModelAndView("update_employee");
        modelAndView.addObject("employee", employeeService.getEmployeeById(id));
        return modelAndView;
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String showSearchPage(Model model) {
        return "search";
    }

    @PostMapping("/search")
    public String searchEmployees(@RequestParam String keyword, Model model) {
        List<Employee> searchResults = employeeService.searchEmployees(keyword);
        model.addAttribute("employees", searchResults);
        return "search";
    }


    @GetMapping("/findPaginated/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir) {

        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("currentPage", pageNo);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalItems", page.getTotalElements());
        modelAndView.addObject("sortField", sortField);
        modelAndView.addObject("sortDir", sortDir);
        modelAndView.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("listEmployees", listEmployees);

        return modelAndView;
    }
}