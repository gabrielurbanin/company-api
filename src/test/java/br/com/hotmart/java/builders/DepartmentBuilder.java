package br.com.hotmart.java.builders;

import br.com.hotmart.java.entities.Department;

public class DepartmentBuilder {

    public static Department buildDepartment(String name) {
        Department department = new Department();
        department.setId(1L);
        department.setName(name);
        department.setBudget(1000);
        department.setNumber(1);

        return department;
    }

    public static Department buildDepartmentWithBudget(String name, Integer budget) {
        Department department = new Department();
        department.setId(1L);
        department.setName(name);
        department.setBudget(budget);
        department.setNumber(1);

        return department;
    }
}
