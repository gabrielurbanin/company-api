package br.com.hotmart.java.builders;

import br.com.hotmart.java.controllers.forms.ProjectForm;

import java.time.LocalDate;

public class ProjectFormBuilder {

    public static ProjectForm buildProjectForm(String name) {
        ProjectForm projectForm = new ProjectForm();
        projectForm.setName(name);
        projectForm.setDepartmentId(1L);
        projectForm.setCost(1000);
        projectForm.setDueDate(LocalDate.of(2021, 1, 2));

        return projectForm;
    }
}
