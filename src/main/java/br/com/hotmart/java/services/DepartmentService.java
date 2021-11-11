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
        return departmentRepository.findAll().stream()
                .map(DepartmentVO::new).collect(Collectors.toList());
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


    public DepartmentVO update(Long id, DepartmentForm form) {
        Department existingDepartment = findById(id);
        existingDepartment.update(form);

        return new DepartmentVO(departmentRepository.save(existingDepartment));
    }

    public void delete(Long id) {
        departmentRepository.delete(findById(id));
    }

}
