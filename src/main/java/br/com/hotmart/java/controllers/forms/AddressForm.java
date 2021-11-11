package br.com.hotmart.java.controllers.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressForm {

    private String country;

    private String state;

    private String city;

    private String street;

    private String addressCode;
}
