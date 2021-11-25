package br.com.hotmart.java.builders;

import br.com.hotmart.java.controllers.forms.DepartmentForm;

public class DepartmentFormBuilder {

    public static DepartmentForm buildDepartmentForm(String name) {
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setName(name);
        departmentForm.setBudget(1000);
        departmentForm.setNumber(1);

        return departmentForm;
    }
}
