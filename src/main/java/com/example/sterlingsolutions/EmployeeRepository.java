package com.example.sterlingsolutions;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    ArrayList<Employee> findByFirstNameOrLastNameOrDepartment_id(String first, String last, long departmentId);
    //ArrayList<Employee> findByEmployees_Department(String first);
}
