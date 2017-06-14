package com.github.etorres.birthdaygreetings;

import java.time.LocalDate;

public class EmployeeBuilder {

    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String email;

    public static EmployeeBuilder aEmployee() {
        return new EmployeeBuilder();
    }

    public EmployeeBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public EmployeeBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Employee build() {
        return new Employee(lastName, firstName, dateOfBirth, email);
    }

}
