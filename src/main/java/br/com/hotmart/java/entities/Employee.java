package br.com.hotmart.java.entities;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "employee",
        uniqueConstraints = @UniqueConstraint(
                name = "cpf_unique",
                columnNames = "cpf_employee"
        )
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(
            name = "cpf_employee",
            nullable = false
    )
    private String cpf;

    private LocalDate date;

    private Integer gender;

    @OneToOne
    @JoinColumn(
            name = "adress_id",
            referencedColumnName = "id"
    )
    private Adress adress;

    @OneToOne
    @JoinColumn(
            name = "supervisor_id",
            referencedColumnName = "id"
    )
    private Employee supervisor;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = {@JoinColumn(
                    name = "employee_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "project_id",
                    referencedColumnName = "id"
            )}
    )
    private List<Project> projects;

    public Employee(EmployeeForm form) {
        this.name = form.getName();
        this.cpf = form.getCpf();
        this.date = form.getDate();
        this.gender = form.getGender();
        this.projects = form.getProjects();
    }


    public void update(EmployeeForm form) {
        this.name = form.getName();
        this.cpf = form.getCpf();
        this.date = form.getDate();
        this.gender = form.getGender();
//        this.adress = form.getAdress();
//        this.supervisor = form.getSupervisor();
        this.projects = form.getProjects();
    }
}
