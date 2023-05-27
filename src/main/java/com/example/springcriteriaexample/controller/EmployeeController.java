package com.example.springcriteriaexample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcriteriaexample.model.Employee;
import com.example.springcriteriaexample.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
  
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/search-matches")
  public ResponseEntity<List<Employee>> searchEmployees(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email) {
    List<Employee> employees = employeeService.searchEmployeesMatching(firstName, lastName, email);
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/search-contains")
  public ResponseEntity<List<Employee>> searchEmployees2(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email) {
    List<Employee> employees = employeeService.searchEmployeesContaining(firstName, lastName, email);
    return ResponseEntity.ok(employees);
  }
}
