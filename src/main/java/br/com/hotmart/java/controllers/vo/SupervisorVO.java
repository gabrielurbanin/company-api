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

    @Override
    public boolean equals(Object toCompare) {
        if (!(toCompare instanceof SupervisorVO))
            return false;

        SupervisorVO supervisorVO = (SupervisorVO) toCompare;

        if(id == supervisorVO.getId() && name == supervisorVO.getName())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 5 + (id != null ? id.hashCode() : 0);
    }
}
