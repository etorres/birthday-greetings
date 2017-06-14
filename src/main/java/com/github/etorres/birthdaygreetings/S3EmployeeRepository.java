package com.github.etorres.birthdaygreetings;

import java.time.LocalDate;
import java.util.List;

public class S3EmployeeRepository implements EmployeeRepository {

    private final EmployeesReader employeesReader;

    public S3EmployeeRepository(EmployeesReader employeesReader) {
        this.employeesReader = employeesReader;
    }

    @Override
    public List<Employee> findEmployeesBornOn(LocalDate date) {
        throw new IllegalStateException("Not implemented");
    }

}
