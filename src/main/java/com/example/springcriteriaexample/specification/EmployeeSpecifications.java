package com.example.springcriteriaexample.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.springcriteriaexample.model.Department;
import com.example.springcriteriaexample.model.Employee;

import jakarta.persistence.criteria.Join;

public class EmployeeSpecifications {

  public static Specification<Employee> firstNameContains(String firstName) {
    return (root, query, criteriaBuilder) -> firstName == null ? criteriaBuilder.conjunction()
        : criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
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

  public static Specification<Employee> worksInDepartmentNamed(String departmentName) {
    return (root, query, criteriaBuilder) -> {
      if (departmentName == null) {
        return criteriaBuilder.conjunction();
      }
      Join<Employee, Department> departmentJoin = root.join("department");
      return criteriaBuilder.equal(departmentJoin.get("name"), departmentName);
    };
  }

  // equal:
  // public static Specification<Employee> hasId(Long id) {
  // return (root, query, cb) -> id == null ? cb.conjunction() :
  // cb.equal(root.get("id"), id);
  // }

  // between:
  // public static Specification<Employee> hiredBetween(Date start, Date end) {
  // return (root, query, cb) -> {
  // if (start == null || end == null) {
  // return cb.conjunction();
  // }
  // return cb.between(root.get("hireDate"), start, end);
  // };
  // }

  // comparison
  // public static Specification<Employee> hiredBefore(Date date) {
  // return (root, query, cb) -> date == null ? cb.conjunction() :
  // cb.lessThan(root.get("hireDate"), date);
  // }

  // public static Specification<Employee> hiredAfter(Date date) {
  // return (root, query, cb) -> date == null ? cb.conjunction() :
  // cb.greaterThan(root.get("hireDate"), date);
  // }

  // join
  // public static Specification<Employee> worksInDepartmentNamed(String
  // departmentName) {
  // return (root, query, cb) -> {
  // if (departmentName == null) {
  // return cb.conjunction();
  // }
  // Join<Employee, Department> departmentJoin = root.join("departments");
  // return cb.equal(departmentJoin.get("name"), departmentName);
  // };
  // }

  // null checks
  // public static Specification<Employee> nameIsNull() {
  // return (root, query, cb) -> cb.isNull(root.get("name"));
  // }

  // public static Specification<Employee> nameIsNotNull() {
  // return (root, query, cb) -> cb.isNotNull(root.get("name"));
  // }
}
