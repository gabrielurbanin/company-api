package br.com.hotmart.java.entities;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id"
    )
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "project_employee",
            joinColumns = {@JoinColumn(
                    name = "project_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "employee_id",
                    referencedColumnName = "id"
            )}
    )
    private List<Employee> employees;

    public Project(ProjectForm form) {
        this.name = form.getName();
    }

    public void update(ProjectForm form) {
        this.name = form.getName();
    }
}
