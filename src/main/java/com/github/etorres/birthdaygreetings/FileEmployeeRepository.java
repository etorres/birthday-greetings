package com.github.etorres.birthdaygreetings;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {

    private final File employeesFile;
    private final EmployeesReader employeesReader;

    public FileEmployeeRepository(File employeesFile, EmployeesReader employeesReader) {
        this.employeesFile = employeesFile;
        this.employeesReader = employeesReader;
    }

    @Override
    public List<Employee> findEmployeesBornOn(LocalDate date) {
        try (InputStream employeesInputStream = new FileInputStream(employeesFile)) {
            return employeesReader.findEmployeesBornOn(employeesInputStream, date);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read employees records from file: " + employeesFile, e);
        }
    }

}
