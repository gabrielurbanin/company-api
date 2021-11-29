package br.com.hotmart.java.builders;

import br.com.hotmart.java.entities.Address;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import com.google.common.collect.ImmutableList;

import java.time.LocalDate;
import java.util.Collections;

public class EmployeeBuilder {

    public static Employee buildEmployee(String name, Project project) {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName(name);
        employee.setCpf("000000000-00");
        employee.setDate(LocalDate.of(2021, 1, 1));
        employee.setGender(1);
        employee.setSalaryPerHour(10);
        employee.setAddress(prepareAddress());
        employee.setSupervisor(prepareSupervisor());
        employee.setProjects(ImmutableList.of(project));
        employee.setSubordinates(Collections.emptyList());

        return employee;
    }

    public static Employee buildBasicEmployee(String name) {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName(name);
        employee.setCpf("000000000-00");
        employee.setDate(LocalDate.of(2021, 1, 1));
        employee.setGender(1);
        employee.setSalaryPerHour(10);

        return employee;
    }

    private static Address prepareAddress() {
        Address address = new Address();
        address.setId(1L);
        address.setCountry("brazil");
        address.setState("MG");
        address.setCity("BH");
        address.setStreet("street");
        address.setAddressCode("00000-00");

        return address;
    }

    private static Employee prepareSupervisor() {
        Employee supervisor = new Employee();
        supervisor.setId(2L);
        supervisor.setName("supervisor");

        return supervisor;
    }
}
