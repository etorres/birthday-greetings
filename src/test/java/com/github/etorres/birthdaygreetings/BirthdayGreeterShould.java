package com.github.etorres.birthdaygreetings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayGreeterShould {

    private static final LocalDate DATE = LocalDate.of(2017, Month.JUNE, 14);

    private List<Employee> employees = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        employees.add(EmployeeBuilder.aEmployee()
                .withLastName("Doe")
                .withFirstName("John")
                .withDateOfBirth(DATE)
                .withEmail("john.doe@foobar.com")
                .build());
    }

    @Mock
    private Clock clock;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmailSender emailSender;

    @Test
    public void
    sends_a_greetings_email_to_all_employees_whose_birthday_is_today() {
        given(clock.today()).willReturn(DATE);
        given(employeeRepository.findEmployeesBornOn(DATE)).willReturn(employees);

        BirthdayGreeter birthdayGreeter = new BirthdayGreeter(clock, employeeRepository, emailSender);

        birthdayGreeter.sendGreetings();

        verify(emailSender).sendMessage(new EmailAddress("john.doe@foobar.com"),
                new Message("Happy birthday!","Happy birthday, dear John!"));
    }

}