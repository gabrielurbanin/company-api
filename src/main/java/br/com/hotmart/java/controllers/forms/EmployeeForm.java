package br.com.hotmart.java.controllers.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {

    private String name;

    private String cpf;

    private LocalDate date;

    private Integer gender;

    private AdressForm adressForm;

    private Long supervisorId;

    private List<Long> projectId;
}
