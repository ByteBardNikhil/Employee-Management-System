package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long emplId);
    List<EmployeeDto> getAllEmp();
    EmployeeDto updateEmployee(Long empId,EmployeeDto updateEmployee);

    void deleteEmployee(Long empid);
}
