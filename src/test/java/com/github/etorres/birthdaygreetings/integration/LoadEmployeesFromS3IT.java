package com.github.etorres.birthdaygreetings.integration;

import com.github.etorres.birthdaygreetings.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoadEmployeesFromS3IT {

    @Mock
    private Clock clock;

    @Test
    public void
    read_employees_from_S3_file() {
        given(clock.today()).willReturn(LocalDate.of(2017, Month.JUNE, 14));

        EmployeesReader employeesReader = new EmployeesReader();

        EmployeeRepository employeeRepository = new S3EmployeeRepository("ertorser-b1", "employees.txt", employeesReader);

        EmailSender emailSender = new EmailSenderCollaborator();
        EmailSender emailSenderSpy = spy(emailSender);

        BirthdayGreeter birthdayGreeter = new BirthdayGreeter(clock, employeeRepository, emailSenderSpy);

        birthdayGreeter.sendGreetings();

        verify(emailSenderSpy).sendMessage(new EmailAddress("john.doe@foobar.com"),
                new Message("Happy birthday!","Happy birthday, dear John!"));
        verify(emailSenderSpy).sendMessage(new EmailAddress("mary.ann@foobar.com"),
                new Message("Happy birthday!", "Happy birthday, dear Mary!"));
    }

    private class EmailSenderCollaborator extends EmailSender {

        @Override
        public void sendMessage(EmailAddress emailAddress, Message message) {
            // do nothing
        }

    }

}
