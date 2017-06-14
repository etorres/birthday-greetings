package com.github.etorres.birthdaygreetings;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeReader {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static final String EMPLOYEES_HEADER = "last_name, first_name, date_of_birth, email";
    public static final int EMPLOYEE_COLUMNS = 4;

    public static final int LAST_NAME_COLUMN = 0;
    public static final int FIRST_NAME_COLUMN = 1;
    public static final int DATE_OF_BIRTH_COLUMN = 2;
    public static final int EMAIL_COLUMN = 3;

    public List<Employee> filterEmployeesByDateOfBirth(InputStream employeesInputStream, LocalDate date) {
        List<Employee> employees = newEmployeesEmptyList();
        try (Reader reader = new InputStreamReader(employeesInputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (!line.startsWith(EMPLOYEES_HEADER)) {
                    String[] tokens = parseEmployeeLine(line);
                    LocalDate dateOfBirth = parseDateOfBirth(tokens[DATE_OF_BIRTH_COLUMN]);
                    if (dateOfBirth.isEqual(date)) {
                        employees.add(createEmployee(tokens, dateOfBirth));
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read employees records", e);
        }
        return employees;
    }

    private ArrayList<Employee> newEmployeesEmptyList() {
        return new ArrayList<>();
    }

    private String[] parseEmployeeLine(String line) {
        return Arrays.stream(line.split(",", EMPLOYEE_COLUMNS))
                .map(String::trim)
                .toArray(String[]::new);
    }

    private LocalDate parseDateOfBirth(String token) {
        return LocalDate.parse(token.trim(), DATE_TIME_FORMATTER);
    }

    private Employee createEmployee(String[] tokens, LocalDate dateOfBirth) {
        return EmployeeBuilder.aEmployee()
                .withLastName(tokens[LAST_NAME_COLUMN].trim())
                .withFirstName(tokens[FIRST_NAME_COLUMN].trim())
                .withDateOfBirth(dateOfBirth)
                .withEmail(tokens[EMAIL_COLUMN].trim())
                .build();
    }

}
