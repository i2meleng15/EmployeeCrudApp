package com.employee.EmployeeCrud.repository;

import com.employee.EmployeeCrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
