package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Employee;
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
public class EmployeeVO {
    private Long id;

    private String name;

    private String cpf;

    private Integer gender;

    private AddressVO address;

    private SupervisorVO supervisor;

    private List<ProjectVO> projects;

    public EmployeeVO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.gender = employee.getGender();
        this.address = new AddressVO(employee.getAddress());

        this.supervisor = Optional.ofNullable(employee.getSupervisor())
                .map(SupervisorVO::new).orElse(null);

        if (employee.getProjects() == null)
            this.projects = Collections.emptyList();
        else {
            this.projects = employee.getProjects().stream()
                    .map(ProjectVO::new).collect(Collectors.toList());
        }
    }
}
