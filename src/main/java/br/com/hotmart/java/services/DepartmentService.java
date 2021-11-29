package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.BudgetForm;
import br.com.hotmart.java.controllers.forms.DepartmentForm;
import br.com.hotmart.java.controllers.vo.DepartmentVO;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.entities.Project;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    public Page<DepartmentVO> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(DepartmentVO::new);
    }

    public DepartmentVO save(DepartmentForm form) {
        Department newDepartment = new Department(form);
        return new DepartmentVO(departmentRepository.save(newDepartment));
    }

    public Department findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Department does not exist!"));

        return department;
    }

    public DepartmentVO getById(Long id) {
        return new DepartmentVO(findById(id));
    }

    public List<EmployeeVO> getAllEmployees(Long id) {
        return employeeService.getAllEmployeesFromDepartment(id);
    }

    public DepartmentVO update(Long id, DepartmentForm form) {
        Department existingDepartment = findById(id);
        existingDepartment.update(form);

        return new DepartmentVO(departmentRepository.save(existingDepartment));
    }

    public DepartmentVO addBudget(Long id, BudgetForm form) {
        Department department = findById(id);
        department.setBudget(form.getBudget());

        return new DepartmentVO(departmentRepository.save(department));
    }

    public void delete(Long id) {
        departmentRepository.delete(findById(id));
    }

    public String getBudgetStatus(Long id) {
        Department existingDepartment = findById(id);
        List<Project> projects = projectService.getAllByDepartment(id);

        int budget = Optional.ofNullable(existingDepartment.getBudget()).orElse(1);
        int totalCost = projects.stream().mapToInt(Project::getCost).sum();

        double status = (double)totalCost / budget;

        if (status <= 1)
            return "GREEN";
        else if (status <= 1.10)
            return "YELLOW";
        else
            return "RED";
    }
}
