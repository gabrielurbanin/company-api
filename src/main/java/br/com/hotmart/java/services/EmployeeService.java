package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.entities.Adress;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdressService adressService;

    public List<EmployeeVO> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeVO::new).collect(Collectors.toList());
    }

    public void save(EmployeeForm form) {
        Adress adress = adressService.findById(form.getAdressId());
        Employee newEmployee = new Employee(form);
        newEmployee.setAdress(adress);

        employeeRepository.save(newEmployee);
    }

    public EmployeeVO getById(Long id) {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            return new EmployeeVO(employee);
        }

        throw new ResourceNotFoundException("id", "Employee not found.");
    }

    public void update(Long id, EmployeeForm form) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(new Employee());
        existingEmployee.update(form);
        employeeRepository.save(existingEmployee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
