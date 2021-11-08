package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeVO>> getAll() {
        return ResponseEntity.ok().body(employeeService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody EmployeeForm form) {
        employeeService.save(form);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeVO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(employeeService.getById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EmployeeForm form) {
        employeeService.update(id,form);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/{supervisorId}")
    public ResponseEntity<Void> updateSupervisor(@PathVariable Long id, @PathVariable Long supervisorId) {
        employeeService.updateSupervisor(id, supervisorId);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
