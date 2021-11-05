package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Adress;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {

    private String name;

    private String cpf;

    private LocalDate date;

    private Integer gender;

    private Adress adress;

    private Employee supervisor;

    private List<Project> projects;

    public EmployeeVO(Employee employee) {
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.date = employee.getDate();
        this.gender = employee.getGender();
        this.adress = employee.getAdress();
        this.supervisor = employee.getSupervisor();
        this.projects = employee.getProjects();
    }
}
