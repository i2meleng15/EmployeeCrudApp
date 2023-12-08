package com.employee.EmployeeCrud.Repository;

import com.employee.EmployeeCrud.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
