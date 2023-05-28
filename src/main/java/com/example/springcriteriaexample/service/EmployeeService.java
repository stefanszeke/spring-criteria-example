package com.example.springcriteriaexample.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.springcriteriaexample.DTO.EmployeeDTO;
import com.example.springcriteriaexample.Mapper.EmployeeMapper;
import com.example.springcriteriaexample.model.Department;
import com.example.springcriteriaexample.model.Employee;
import com.example.springcriteriaexample.repository.DepartmentRepository;
import com.example.springcriteriaexample.repository.EmployeeRepository;
import com.example.springcriteriaexample.specification.EmployeeSpecifications;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;
  private final EmployeeMapper employeeMapper;

  public EmployeeService(
      EmployeeRepository employeeRepository,
      DepartmentRepository departmentRepository,
      EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.departmentRepository = departmentRepository;
    this.employeeMapper = employeeMapper;
  }

  public List<EmployeeDTO> searchEmployeesMatching(String firstName, String lastName, String email) {
    Specification<Employee> specification = Specification.where(
        EmployeeSpecifications.hasFirstName(firstName))
        .and(EmployeeSpecifications.hasLastName(lastName))
        .and(EmployeeSpecifications.hasEmail(email));
    List<Employee> employees = employeeRepository.findAll(specification);
    return employeeMapper.toEmployeeDTOs(employees);
  }

  public List<EmployeeDTO> searchEmployeesContaining(String firstName, String lastName, String email) {
    Specification<Employee> specification = Specification.where(
        EmployeeSpecifications.firstNameContains(firstName))
        .and(EmployeeSpecifications.lastNameContains(lastName))
        .and(EmployeeSpecifications.emailContains(email));
    List<Employee> employees = employeeRepository.findAll(specification);
    return employeeMapper.toEmployeeDTOs(employees);
  }

  public List<EmployeeDTO> getEmployeesInDepartment(String departmentName) {
    Specification<Employee> specification = Specification.where(
        EmployeeSpecifications.worksInDepartmentNamed(departmentName));
      
    List<Employee> employees = employeeRepository.findAll(specification);
    return employeeMapper.toEmployeeDTOs(employees);
  }

  public void populateDatabase() {
    Department department1 = new Department();
    department1.setName("IT");
    Department department2 = new Department();
    department2.setName("HR");

    Employee employee1 = new Employee("John", "Doe", "John@gmail.com", department1);
    Employee employee2 = new Employee("John", "Doe", "John@gmail.com", department1);
    Employee employee3 = new Employee("John", "Smith", "Smith@gmail.com", department2);
    Employee employee4 = new Employee("George", "George", "George@George.com", department1);

    departmentRepository.save(department1);
    departmentRepository.save(department2);

    employeeRepository.save(employee1);
    employeeRepository.save(employee2);
    employeeRepository.save(employee3);
    employeeRepository.save(employee4);
  }

}
