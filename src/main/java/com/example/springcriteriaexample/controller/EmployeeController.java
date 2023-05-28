package com.example.springcriteriaexample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcriteriaexample.DTO.EmployeeDTO;
import com.example.springcriteriaexample.model.Employee;
import com.example.springcriteriaexample.service.EmployeeService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
  
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/search-matches")
  public ResponseEntity<List<EmployeeDTO>> searchEmployees(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email) {
    List<EmployeeDTO> employees = employeeService.searchEmployeesMatching(firstName, lastName, email);
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/search-contains")
  public ResponseEntity<List<EmployeeDTO>> searchEmployees2(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email) {
    List<EmployeeDTO> employees = employeeService.searchEmployeesContaining(firstName, lastName, email);
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/search-department/{departmentName}")
  public ResponseEntity<List<EmployeeDTO>> searchEmployees3(
      @PathVariable("departmentName") String departmentName
    ) {
      System.out.println("departmentName: " + departmentName);
    List<EmployeeDTO> employees = employeeService.getEmployeesInDepartment(departmentName);
    return ResponseEntity.ok(employees);
  }
}
