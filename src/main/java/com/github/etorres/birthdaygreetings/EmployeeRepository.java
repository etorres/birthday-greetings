package com.github.etorres.birthdaygreetings;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository {

    List<Employee> findEmployeesBornOn(LocalDate date);

}
