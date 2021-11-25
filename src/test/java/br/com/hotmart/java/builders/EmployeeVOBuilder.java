package br.com.hotmart.java.builders;

import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.controllers.vo.SupervisorVO;

public class EmployeeVOBuilder {

    public static EmployeeVO buildEmployeeVO(String name) {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId(1L);
        employeeVO.setName(name);
        employeeVO.setSupervisor(prepareSupervisor());

        return employeeVO;
    }

    private static SupervisorVO prepareSupervisor() {
        SupervisorVO supervisorVO = new SupervisorVO();
        supervisorVO.setId(2L);
        supervisorVO.setName("supervisor");

        return supervisorVO;
    }
}
