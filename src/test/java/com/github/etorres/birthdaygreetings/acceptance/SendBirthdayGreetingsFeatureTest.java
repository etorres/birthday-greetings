package com.github.etorres.birthdaygreetings.acceptance;

import com.github.etorres.birthdaygreetings.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SendBirthdayGreetingsFeatureTest {

    @Mock
    private Clock clock;

    @Test
    public void
    sends_a_greetings_email_to_all_employees_whose_birthday_is_today() {
        given(clock.today()).willReturn(LocalDate.of(2017, Month.JUNE, 14));

        ClassLoader classLoader = getClass().getClassLoader();
        File employeesFile = new File(classLoader.getResource("file/employees.txt").getFile());
        EmployeeReader employeeReader = new EmployeeReader();

        EmployeeRepository employeeRepository = new FileEmployeeRepository(employeesFile, employeeReader);

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
        public void sendMessage(EmailAddress destination, Message message) {
            // do nothing
        }

    }

}
