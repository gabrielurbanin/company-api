package br.com.hotmart.java.entities;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Adress adress;

    @OneToOne
    private Employee supervisor;
}