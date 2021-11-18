package br.com.hotmart.java.controllers.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {

    private String name;

    private String cpf;

    private Integer gender;

    private AddressForm addressForm;

    private Integer salaryPerHour;

}
