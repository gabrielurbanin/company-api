package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorVO {
    private Long id;
    private String name;

    public SupervisorVO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
    }
}
