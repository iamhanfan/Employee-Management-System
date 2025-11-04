package com.ems.app.sys.repository;

import com.ems.app.sys.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT COALESCE(MAX(e.id), 0) FROM Employee e")
    Long findMaxId();
}