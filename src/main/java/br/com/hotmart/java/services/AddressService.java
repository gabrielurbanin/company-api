package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.AddressForm;
import br.com.hotmart.java.controllers.vo.AddressVO;
import br.com.hotmart.java.entities.Address;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressVO> getAll() {
        return addressRepository.findAll().stream()
                .map(AddressVO::new).collect(Collectors.toList());
    }

    public AddressVO save(AddressForm form) {
        Address newAddress = new Address(form);

        return new AddressVO(addressRepository.save(newAddress));
    }

    public Address findById(Long id) {
        Address address = addressRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("id", "Address does not exist!"));

        return address;
    }

    public AddressVO getById(Long id) {
        return new AddressVO(findById(id));
    }

    public AddressVO update(Long id, AddressForm form) {
        Address existingAddress = findById(id);
        existingAddress.update(form);

        return new AddressVO(addressRepository.save(existingAddress));
    }

    public void delete(Long id) {
        addressRepository.delete(findById(id));
    }
}
