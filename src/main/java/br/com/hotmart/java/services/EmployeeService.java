package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.entities.Address;
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

    public List<EmployeeVO> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeVO::new).collect(Collectors.toList());
    }

    public EmployeeVO save(EmployeeForm form) {
        Employee newEmployee = new Employee(form);

        newEmployee.setAddress(new Address(form.getAddressForm()));

        return new EmployeeVO(employeeRepository.save(newEmployee));
    }

    public Employee findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Employee does not exist!"));

        return employee;
    }

    public EmployeeVO getById(Long id) {
        return new EmployeeVO(findById(id));
    }

    public EmployeeVO update(Long id, EmployeeForm form) {
        Employee existingEmployee = findById(id);
        existingEmployee.update(form);

        return new EmployeeVO(employeeRepository.save(existingEmployee));
    }

    public EmployeeVO updateSupervisor(Long employeeId, Long supervisorId) {
        Employee existingEmployee = findById(employeeId);
        existingEmployee.setSupervisor(findById(supervisorId));

        return new EmployeeVO(employeeRepository.save(existingEmployee));
    }

    public void delete(Long id) {
        employeeRepository.delete(findById(id));
    }
}
