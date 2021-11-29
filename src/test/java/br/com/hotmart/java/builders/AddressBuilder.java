package br.com.hotmart.java.builders;

import br.com.hotmart.java.entities.Address;

public class AddressBuilder {

    public static Address buildAddress(String street) {
        Address address = new Address();
        address.setId(1L);
        address.setCountry("brazil");
        address.setState("MG");
        address.setCity("BH");
        address.setStreet(street);
        address.setAddressCode("00000-00");

        return address;
    }
}
