package com.github.etorres.birthdaygreetings;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {

    private final File employeesFile;
    private final EmployeeReader employeeReader;

    public FileEmployeeRepository(File employeesFile, EmployeeReader employeeReader) {
        this.employeesFile = employeesFile;
        this.employeeReader = employeeReader;
    }

    @Override
    public List<Employee> findEmployeesBornOn(LocalDate date) {
        try (InputStream employeesInputStream = new FileInputStream(employeesFile)) {
            return employeeReader.filterEmployeesByDateOfBirth(employeesInputStream, date);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read employees records from file: " + employeesFile, e);
        }
    }

}
