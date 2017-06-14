package com.github.etorres.birthdaygreetings;

import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileEmployeeRepositoryShould {

    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(2017, Month.JUNE, 14);
    private static final LocalDate ANOTHER_DATE = LocalDate.of(2016, Month.FEBRUARY, 21);

    @Test
    public void
    find_employees_born_on_given_date() {
        ClassLoader classLoader = getClass().getClassLoader();
        File employeesFile = new File(classLoader.getResource("file/employees.txt").getFile());

        EmployeeReader employeeReader = new EmployeeReader();

        EmployeeRepository employeeRepository = new FileEmployeeRepository(employeesFile, employeeReader);

        List<Employee> employees = employeeRepository.findEmployeesBornOn(DATE_OF_BIRTH);

        assertThat(employees).hasSize(2).contains(
                EmployeeBuilder.aEmployee().withLastName("Doe").withFirstName("John").withDateOfBirth(DATE_OF_BIRTH).withEmail("john.doe@foobar.com").build(),
                EmployeeBuilder.aEmployee().withLastName("Ann").withFirstName("Mary").withDateOfBirth(DATE_OF_BIRTH).withEmail("mary.ann@foobar.com").build()
        ).doesNotContain(
                EmployeeBuilder.aEmployee().withLastName("Roe").withFirstName("Jane").withDateOfBirth(ANOTHER_DATE).withEmail("john.doe@foobar.com").build()
        );
    }

}