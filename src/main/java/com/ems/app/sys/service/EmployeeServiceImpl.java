package com.ems.app.sys.service;

import com.ems.app.sys.model.Employee;
import com.ems.app.sys.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public synchronized void saveEmployee(Employee employee) {
        // If the employee is new (id is 0), generate a new ID
        if (employee.getId() == 0) {
            Long maxId = employeeRepository.findMaxId();
            employee.setId(maxId + 1);
        }
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllEmployees() {
        this.employeeRepository.deleteAll();
    }
}