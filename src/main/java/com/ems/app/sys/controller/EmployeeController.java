package com.ems.app.sys.controller;

import com.ems.app.sys.model.Employee;
import com.ems.app.sys.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Display the new dashboard home page
    @GetMapping("/")
    public String viewHomePage() {
        return "index"; // Renders the new dashboard index.html
    }

    // Display list of all employees
    @GetMapping("/employees")
    public String viewEmployeeList(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "employee-list"; // Renders employee-list.html
    }

    // Show form to add a new employee
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-employee";
    }

    // Save or update an employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return (employee.getId() == 0) ? "add-employee" : "update-employee";
        }
        boolean isNew = employee.getId() == 0;
        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee " + (isNew ? "added" : "updated") + " successfully!");
        return "redirect:/employees";
    }

    // Show form to update an employee's details
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    // Delete an employee by ID from the list page
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        this.employeeService.deleteEmployeeById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
        return "redirect:/employees";
    }

    // Delete an employee by ID from the dashboard modal
    @PostMapping("/deleteEmployeeById")
    public String deleteEmployeeByIdFromModal(@RequestParam("id") long id, RedirectAttributes redirectAttributes) {
        try {
            // Check if employee exists before deleting
            employeeService.getEmployeeById(id); 
            this.employeeService.deleteEmployeeById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Employee with ID " + id + " deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: Employee with ID " + id + " not found.");
        }
        return "redirect:/employees";
    }

    // Delete all employees after confirmation
    @GetMapping("/deleteAllEmployees")
    public String deleteAllEmployees(RedirectAttributes redirectAttributes) {
        this.employeeService.deleteAllEmployees();
        redirectAttributes.addFlashAttribute("successMessage", "All employees have been deleted successfully!");
        return "redirect:/employees";
    }
}
