package com.github.etorres.birthdaygreetings;

import java.util.List;

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
        List<Employee> employees = employeeRepository.findEmployeesBornOn(clock.today());
        employees.forEach(employee -> emailSender.sendMessage(new EmailAddress(employee.email()),
                greetingsMessage(employee)));
    }

    private Message greetingsMessage(Employee employee) {
        return new Message("Happy birthday!", String.format("Happy birthday, dear %s!", employee.firstName()));
    }

}
