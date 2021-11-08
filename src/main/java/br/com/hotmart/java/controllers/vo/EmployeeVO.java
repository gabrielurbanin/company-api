package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {

    private String name;

    private String cpf;

    private LocalDate date;

    private Integer gender;

    private AdressVO adress;

    private EmployeeVO supervisor;

    private List<ProjectVO> projects;

    public EmployeeVO(Employee employee) {
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.date = employee.getDate();
        this.gender = employee.getGender();
        this.adress = new AdressVO(employee.getAdress());
        this.supervisor = new EmployeeVO(employee.getSupervisor());
        this.projects = employee.getProjects().stream().map(ProjectVO::new).collect(Collectors.toList());
    }
}
