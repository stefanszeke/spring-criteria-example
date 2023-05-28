package com.example.springcriteriaexample.DTO;

import com.example.springcriteriaexample.model.Department;

import lombok.Data;

@Data
public class EmployeeDTO {
  
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private DepartmentDTO department;
}
