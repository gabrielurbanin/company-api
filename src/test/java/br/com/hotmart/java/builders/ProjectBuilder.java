package br.com.hotmart.java.builders;

import br.com.hotmart.java.entities.Address;
import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import com.google.common.collect.ImmutableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProjectBuilder {

    public static Project buildProject(String name) {
        Project project = buildBasicProject(name);
        project.setDepartment(prepareDepartment());

        List<Employee> employees = new ArrayList<>();
        employees.add(prepareEmployee("name1", project));
        employees.add(prepareEmployee("name2", project));

        project.setEmployees(employees);

        return project;
    }

    public static Project buildProjectWithCost(String name, Integer cost) {
        Project project = buildBasicProject(name);
        project.setCost(cost);
        project.setDepartment(prepareDepartment());
        project.setEmployees(List.of(prepareEmployee("name1", project), prepareEmployee("name2", project)));

        return project;
    }

    private static Project buildBasicProject(String name) {
        Project basicProject = new Project();
        basicProject.setId(1L);
        basicProject.setName(name);
        basicProject.setCost(1000);
        basicProject.setStartDate(LocalDate.of(2021, 1, 1));
        basicProject.setDueDate(LocalDate.of(2021, 1, 2));

        return basicProject;
    }

    private static Department prepareDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setName("department");
        department.setNumber(1);
        department.setBudget(1000);

        return department;
    }

    private static Employee prepareEmployee(String name, Project project) {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName(name);
        employee.setCpf("000000000-00");
        employee.setDate(LocalDate.of(2021, 1, 1));
        employee.setGender(1);
        employee.setSalaryPerHour(10);
        employee.setAddress(prepareAddress());
        employee.setSupervisor(prepareSupervisor());
        employee.setProjects(List.of(project));
        employee.setSubordinates(Collections.emptyList());

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
