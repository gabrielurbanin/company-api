package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<EmployeeVO> save(@RequestBody EmployeeForm form, UriComponentsBuilder uriBuilder) {

        EmployeeVO savedEmployee = employeeService.save(form);
        URI uri = uriBuilder.path("/departments/{id}").buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(uri).body(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeVO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(employeeService.getById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<EmployeeVO> findByName(@RequestParam String name) {
        return ResponseEntity.ok().body(employeeService.findByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeVO> update(@PathVariable Long id, @RequestBody EmployeeForm form) {
        return ResponseEntity.ok().body(employeeService.update(id,form));
    }

    @PatchMapping("/{id}/supervisor/{supervisorId}")
    public ResponseEntity<EmployeeVO> updateSupervisor(@PathVariable Long id, @PathVariable Long supervisorId) {
        return ResponseEntity.accepted().body(employeeService.updateSupervisor(id, supervisorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
