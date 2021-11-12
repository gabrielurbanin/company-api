package br.com.hotmart.java.entities;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
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

    private LocalDate date = LocalDate.now();

    private Integer gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "adress_id",
            referencedColumnName = "id"
    )
    private Address address;

    @ManyToOne
    @JoinColumn(
            name = "supervisorId",
            referencedColumnName = "id"
    )
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> subordinates;

    @ManyToMany(mappedBy = "employees")
    private List<Project> projects;

    public Employee(EmployeeForm form) {
        this.name = form.getName();
        this.cpf = form.getCpf();
        this.gender = form.getGender();
    }

    public void update(EmployeeForm form) {
        this.name = form.getName();
        this.cpf = form.getCpf();
        this.gender = form.getGender();
    }

}
