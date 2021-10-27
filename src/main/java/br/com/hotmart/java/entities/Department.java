package br.com.hotmart.java.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
