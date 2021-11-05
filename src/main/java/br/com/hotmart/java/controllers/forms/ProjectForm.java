package br.com.hotmart.java.controllers.forms;

import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForm {

    private String name;

    private Department department;

    private List<Employee> employees;
}
