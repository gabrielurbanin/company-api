package br.com.hotmart.java.controllers.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForm {

    private String name;

    private Long departmentId;

    private Integer cost;

    private LocalDate dueDate;
}
