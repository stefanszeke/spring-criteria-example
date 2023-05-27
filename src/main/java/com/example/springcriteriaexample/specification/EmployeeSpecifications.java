package com.example.springcriteriaexample.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.springcriteriaexample.model.Employee;

public class EmployeeSpecifications {

  public static Specification<Employee> firstNameContains(String firstName) {
    return (root, query, criteriaBuilder) -> {
      if (firstName == null)
        return criteriaBuilder.conjunction();

      return criteriaBuilder.like(
          criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    };
  }

  public static Specification<Employee> lastNameContains(String lastName) {
    return (root, query, criteriaBuilder) -> {
      if (lastName == null)
        return criteriaBuilder.conjunction();

      return criteriaBuilder.like(
          criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    };
  }

  public static Specification<Employee> emailContains(String email) {
    return (root, query, criteriaBuilder) -> {
      if (email == null)
        return criteriaBuilder.conjunction();

      return criteriaBuilder.like(
          criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    };
  }

  public static Specification<Employee> firstNameStartsWith(String firstName) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstName"), firstName + "%");
  }

  public static Specification<Employee> lastNameStartsWith(String lastName) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("lastName"), lastName + "%");
  }

  public static Specification<Employee> emailStartsWith(String email) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), email + "%");
  }

  public static Specification<Employee> hasFirstName(String firstName) {
    return (root, query, criteriaBuilder) -> {
      if (firstName == null) {
        return criteriaBuilder.conjunction(); // if firstName is null, return 1=1 (always true), so that the query will
                                              // not filter on firstName
      }
      return criteriaBuilder.equal(root.get("firstName"), firstName);
    };
  }

  public static Specification<Employee> hasLastName(String lastName) {
    return (root, query, criteriaBuilder) -> {
      if (lastName == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.equal(root.get("lastName"), lastName);
    };
  }

  public static Specification<Employee> hasEmail(String email) {
    return (root, query, criteriaBuilder) -> {
      if (email == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.equal(root.get("email"), email);
    };
  }
}
