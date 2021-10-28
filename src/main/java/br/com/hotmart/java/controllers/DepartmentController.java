package br.com.hotmart.java.controllers;

import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/company/rest/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Department add(@RequestBody Department newDepartment) {
        return departmentService.saveDepartment(newDepartment);
    }

    @GetMapping("/getAll")
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/getById/{departmentId}")
    public Department getById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/update/{departmentId}")
    public String update(@PathVariable Long departmentId, @RequestBody Department updatedDepartment) {
        return departmentService.updateDepartment(departmentId, updatedDepartment);
    }

    @GetMapping("/delete/{departmentId}")
    public String delete(@PathVariable Long departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }

}
