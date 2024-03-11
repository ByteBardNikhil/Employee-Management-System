package net.javaguides.ems.controller;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Build add Employee Rest API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmp = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    //Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long empId) {
        try {
            EmployeeDto empDto = employeeService.getEmployeeById(empId);
            if (empDto != null) {
                return ResponseEntity.ok(empDto); // Employee found, return 200 OK with the employee DTO
            } else {
                return ResponseEntity.notFound().build(); // Employee not found, return 404 Not Found
            }
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build(); // Employee not found, return 404 Not Found
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Internal server error
        }
    }


    //Build an API to get all Employees
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee()
    {
        List<EmployeeDto> employees=employeeService.getAllEmp();
        return ResponseEntity.ok(employees);


    }

    //Build Update Employee REST API
    @PutMapping("{id}") // Corrected path variable
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId, @RequestBody EmployeeDto upEmp) {
        EmployeeDto empDto = employeeService.updateEmployee(empId, upEmp);
        return ResponseEntity.ok(empDto);
    }
 //Buld Delete Employee Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empid)
    {
        employeeService.deleteEmployee(empid);
        return ResponseEntity.ok("Employee Deletes Succesfully");
    }

}
