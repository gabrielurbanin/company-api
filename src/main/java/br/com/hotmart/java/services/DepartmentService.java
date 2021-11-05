package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.DepartmentForm;
import br.com.hotmart.java.controllers.vo.DepartmentVO;
import br.com.hotmart.java.entities.Department;
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
        try {
            return new DepartmentVO(departmentRepository.findById(id).orElse(null));
        } catch (NullPointerException e) {
            return new DepartmentVO();
        }
    }

    public void update(Long id, DepartmentForm form) {
        Department existingDepartment = departmentRepository.findById(id).orElse(null);
        existingDepartment.update(form);
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}
