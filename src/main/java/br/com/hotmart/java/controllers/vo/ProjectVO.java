package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVO {

    private String name;

    private Department department;

    private List<Employee> employees;

    public ProjectVO(Project project) {
        this.name = project.getName();
        this.department = project.getDepartment();
        this.employees = project.getEmployees();
    }
}
