package com.example.springcriteriaexample.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.springcriteriaexample.DTO.DepartmentDTO;
import com.example.springcriteriaexample.DTO.EmployeeDTO;
import com.example.springcriteriaexample.model.Department;
import com.example.springcriteriaexample.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "email", target = "email")
  EmployeeDTO toEmployeeDTO(Employee employee);

  List<EmployeeDTO> toEmployeeDTOs(List<Employee> employees);

  DepartmentDTO toDepartmentDTO(Department department);

}
