package br.com.hotmart.java.entities;


import br.com.hotmart.java.controllers.forms.DepartmentForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "department",
        uniqueConstraints = @UniqueConstraint(
                name = "number_unique",
                columnNames = "department_number"
        )
)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(
            name = "department_number",
            nullable = false
    )
    private Integer number;

    public Department(DepartmentForm form) {
        this.number = form.getNumber();
        this.name = form.getName();
    }

    public void update(DepartmentForm form) {
        this.number = form.getNumber();
        this.name = form.getName();
    }
}
