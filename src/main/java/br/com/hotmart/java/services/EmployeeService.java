package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.entities.Adress;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectService projectService;

    public List<EmployeeVO> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeVO::new).collect(Collectors.toList());
    }

    public void save(EmployeeForm form) {
        Employee newEmployee = new Employee(form);

        newEmployee.setAdress(new Adress(form.getAdressForm()));

        if (form.getSupervisorId() != null)
            newEmployee.setSupervisor(this.findById(form.getSupervisorId()));

        newEmployee.setProjects(projectService.findAllById(form.getProjectId()));

        employeeRepository.save(newEmployee);
    }

    public EmployeeVO getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            return new EmployeeVO(employee);
        }

        throw new ResourceNotFoundException("id", "Employee does not exist!");
    }

    public Employee findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException("id", "Employee does not exist!");
        }

        return employee;
    }

    public void update(Long id, EmployeeForm form) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee == null) {
            throw new ResourceNotFoundException("id", "Employee does not exist!");
        }

        existingEmployee.update(form);
        employeeRepository.save(existingEmployee);
    }

    public void updateSupervisor(Long employeeId, Long supervisorId) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);

        if (existingEmployee == null) {
            throw new ResourceNotFoundException("id", "Employee does not exist!");
        }

        existingEmployee.setSupervisor(this.findById(supervisorId));
        employeeRepository.save(existingEmployee);
    }

    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException("id", "Employee does not exist!");
        }

        employeeRepository.deleteById(id);
    }
}
