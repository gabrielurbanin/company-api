package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {
    private Long id;

    private String name;

    private SupervisorVO supervisor;

    public EmployeeVO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();

        this.supervisor = Optional.ofNullable(employee.getSupervisor())
                .map(SupervisorVO::new).orElse(null);
    }
}
