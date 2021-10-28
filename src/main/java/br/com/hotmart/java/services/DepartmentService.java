package br.com.hotmart.java.services;

import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department newDepartment) {
        return departmentRepository.save(newDepartment);
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public String updateDepartment(Long departmentId, Department newDepartment) {
        Department existingDepartment = departmentRepository
                .findById(departmentId).orElse(null);

        existingDepartment.setName(newDepartment.getName());
        existingDepartment.setNumber(newDepartment.getNumber());
        departmentRepository.save(existingDepartment);

        return "Successfully updated department " + departmentId.toString() + "!";
    }

    public String deleteDepartment(Long departmentId) {
        try {
            departmentRepository.deleteById(departmentId);
        } catch (EmptyResultDataAccessException e) {
            return "There's no corresponding match to that id.";
        }

        return "Successfully deleted department " + departmentId.toString() + "!";
    }
}
