package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVO {
    private Long id;

    private String name;

    private Integer cost;

    private DepartmentVO department;

    private List<Long> employeesId;

    public ProjectVO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.cost = project.getCost();

        this.department = Optional.ofNullable(project.getDepartment())
                .map(DepartmentVO::new).orElse(null);

        if (project.getEmployees() == null)
            this.employeesId = Collections.emptyList();
        else {
            this.employeesId =  project.getEmployees().stream()
                    .map(Employee::getId).collect(Collectors.toList());
        }

    }
}
