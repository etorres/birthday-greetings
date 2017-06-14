package com.github.etorres.birthdaygreetings;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private final String lastName;
    private final String firstName;
    private final LocalDate dateOfBirth;
    private final String email;

    public Employee(String lastName, String firstName, LocalDate dateOfBirth, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String email() {
        return email;
    }

    public String firstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(lastName, employee.lastName) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dateOfBirth, email);
    }

}
