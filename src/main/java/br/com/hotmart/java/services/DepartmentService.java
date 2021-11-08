package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.DepartmentForm;
import br.com.hotmart.java.controllers.vo.DepartmentVO;
import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentVO> getAll() {
        return departmentRepository.findAll().stream().map(DepartmentVO::new).collect(Collectors.toList());
    }

    public void save(DepartmentForm form) {
        Department newDepartment = new Department(form);
        departmentRepository.save(newDepartment);
    }

    public DepartmentVO getById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null) {
            throw new ResourceNotFoundException("id", "Department does not exist!");
        }

        return new DepartmentVO(department);
    }

    public Department findById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null) {
            throw new ResourceNotFoundException("id", "Department does not exist!");
        }

        return department;
    }

    public void update(Long id, DepartmentForm form) {
        Department existingDepartment = departmentRepository.findById(id).orElse(null);

        if (existingDepartment == null) {
            throw new ResourceNotFoundException("id", "Department does not exist!");
        }

        existingDepartment.update(form);
        departmentRepository.save(existingDepartment);
    }

    public void delete(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null) {
            throw new ResourceNotFoundException("id", "Department does not exist!");
        }

        departmentRepository.deleteById(id);
    }

}
