package com.employee.EmployeeCrud.service;

import com.employee.EmployeeCrud.dto.UserRegistrationDto;
import com.employee.EmployeeCrud.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
