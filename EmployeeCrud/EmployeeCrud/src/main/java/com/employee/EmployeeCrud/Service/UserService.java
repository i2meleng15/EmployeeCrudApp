package com.employee.EmployeeCrud.Service;

import com.employee.EmployeeCrud.DTO.UserRegistrationDto;
import com.employee.EmployeeCrud.Entity.User;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
