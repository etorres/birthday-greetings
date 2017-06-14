package com.github.etorres.birthdaygreetings;

public class BirthdayGreeter {

    private final Clock clock;
    private final EmployeeRepository employeeRepository;
    private final EmailSender emailSender;

    public BirthdayGreeter(Clock clock, EmployeeRepository employeeRepository, EmailSender emailSender) {
        this.clock = clock;
        this.employeeRepository = employeeRepository;
        this.emailSender = emailSender;
    }

    public void sendGreetings() {
        throw new IllegalStateException("Not implemented");
    }

}
