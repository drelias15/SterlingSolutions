package com.example.sterlingsolutions;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    ArrayList<Employee> findByFirstNameOrLastName(String first, String last);
    ArrayList<Employee> findByDepartment_id(long departmentId);
}
