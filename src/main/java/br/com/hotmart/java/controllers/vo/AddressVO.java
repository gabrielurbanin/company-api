package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {
    private Long id;

    private String state;

    private String city;

    private String street;

    public AddressVO(Address address) {
        this.id = address.getId();
        this.state = address.getState();
        this.city = address.getCity();
        this.street = address.getStreet();
    }

    @Override
    public boolean equals(Object toCompare) {
        if (!(toCompare instanceof AddressVO))
            return false;

        AddressVO addressVO = (AddressVO) toCompare;

        if (id == addressVO.getId())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 5 + (id != null ? id.hashCode() : 0);
    }
}
