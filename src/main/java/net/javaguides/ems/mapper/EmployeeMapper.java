package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee)
    {
        return new EmployeeDto(
          employee.getId(),

          employee.getFirstName(),
          employee.getLastName(),
                employee.getEmail()
        );
    }
    public static Employee mapToEmployee(EmployeeDto emp)
    {
        return new Employee(
                emp.getId(),
                emp.getFirstName()
                ,emp.getLastName(),
                emp.getEmail()
        );
    }
}
