package com.ems.app.sys.service;

import com.ems.app.sys.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    void deleteAllEmployees();
}
