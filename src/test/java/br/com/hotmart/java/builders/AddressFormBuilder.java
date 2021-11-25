package br.com.hotmart.java.builders;

import br.com.hotmart.java.controllers.forms.AddressForm;

public class AddressFormBuilder {
    public static AddressForm buildAddressForm(String street) {
        AddressForm addressForm = new AddressForm();
        addressForm.setCountry("brazil");
        addressForm.setState("MG");
        addressForm.setCity("BH");
        addressForm.setStreet(street);
        addressForm.setAddressCode("00000-00");

        return addressForm;
    }
}
