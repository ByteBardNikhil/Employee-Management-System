package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResouceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
    Employee savedEmp= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmp);
    }

    @Override
    public EmployeeDto getEmployeeById(Long emplId) {
      Employee emp=  employeeRepository.findById(emplId).orElseThrow(()-> new ResouceNotFoundException("Employee Not Exist With given Id"+emplId));

return EmployeeMapper.mapToEmployeeDto(emp);
    }

    @Override
    public List<EmployeeDto> getAllEmp() {
       List<Employee> ls=employeeRepository.findAll();
    return ls.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());

    }

    @Override
    public EmployeeDto updateEmployee(Long empId, EmployeeDto updateEmployee) {
       Employee emp= employeeRepository.findById(empId).orElseThrow(
                ()->new ResouceNotFoundException("Employee is not exist with given Id" +empId)
        );
       emp.setFirstName(updateEmployee.getFirstName());
       emp.setLastName(updateEmployee.getLastName());
       emp.setEmail(updateEmployee.getEmail());
       Employee upEmp=employeeRepository.save(emp);
       return EmployeeMapper.mapToEmployeeDto(upEmp);
    }

    @Override
    public void deleteEmployee(Long empid) {
        Employee emp=employeeRepository.findById(empid).orElseThrow(
                ()->new ResouceNotFoundException("Do not Exist "+empid)
        );
        employeeRepository.deleteById(empid);


    }
}
