package com.example.springcriteriaexample.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.springcriteriaexample.model.Employee;
import com.example.springcriteriaexample.repository.EmployeeRepository;
import com.example.springcriteriaexample.specification.EmployeeSpecifications;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> searchEmployeesMatching(String firstName, String lastName, String email) {
    Specification<Employee> specification = Specification.where(
        EmployeeSpecifications.hasFirstName(firstName))
        .and(EmployeeSpecifications.hasLastName(lastName))
        .and(EmployeeSpecifications.hasEmail(email));
    return employeeRepository.findAll(specification);
  }

  public List<Employee> searchEmployeesContaining(String firstName, String lastName, String email) {
    Specification<Employee> specification = Specification.where(
        EmployeeSpecifications.firstNameContains(firstName))
        .and(EmployeeSpecifications.lastNameContains(lastName))
        .and(EmployeeSpecifications.emailContains(email));
    return employeeRepository.findAll(specification);
  }


  public void populateDatabase() {
    employeeRepository.save(new Employee("John","Doe", "John@gmail.com"));
    employeeRepository.save(new Employee("Jane","Doe", "Jane@gmail.com"));
    employeeRepository.save(new Employee("John","Smith", "Smith@gmail.com"));
    employeeRepository.save(new Employee("George","George", "George@George.com"));
  }

}
