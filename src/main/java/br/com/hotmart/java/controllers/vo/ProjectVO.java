package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVO {

    private String name;

    private DepartmentVO department;

    private List<Long> employeeId;

    public ProjectVO(Project project) {
        this.name = project.getName();
        this.department = new DepartmentVO(project.getDepartment());
        this.employeeId = project.getEmployees().stream().map(Employee::getId).collect(Collectors.toList());
    }
}
