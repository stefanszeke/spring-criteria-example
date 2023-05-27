package com.example.springcriteriaexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springcriteriaexample.service.EmployeeService;

@SpringBootApplication
public class SpringCriteriaExampleApplication implements CommandLineRunner {

  private final EmployeeService employeeService;

  public SpringCriteriaExampleApplication(EmployeeService employeeService) {
    this.employeeService = employeeService;

  }

	public static void main(String[] args) {
		SpringApplication.run(SpringCriteriaExampleApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    
    System.out.println("\nPopulating database...\n");
    employeeService.populateDatabase();

    System.out.println("\nServer started...\n");
  }

}
