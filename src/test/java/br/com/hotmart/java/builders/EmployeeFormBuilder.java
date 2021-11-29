package br.com.hotmart.java.builders;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.entities.Employee;

import java.time.LocalDate;

import static br.com.hotmart.java.builders.AddressFormBuilder.buildAddressForm;

public class EmployeeFormBuilder {

    public static EmployeeForm buildEmployeeForm(String name) {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setName(name);
        employeeForm.setCpf("000000000-00");
        employeeForm.setGender(1);
        employeeForm.setSalaryPerHour(10);
        employeeForm.setAddressForm(buildAddressForm("street"));

        return employeeForm;
    }
}
