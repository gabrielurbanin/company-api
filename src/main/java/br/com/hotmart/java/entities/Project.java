package br.com.hotmart.java.entities;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

    public Project(ProjectForm form) {
        this.name = form.getName();
    }

    public void update(ProjectForm form) {
        this.name = form.getName();
    }
}
