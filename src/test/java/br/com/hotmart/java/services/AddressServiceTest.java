package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.vo.AddressVO;
import br.com.hotmart.java.entities.Address;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void givenAddressId_whenFindById_thenAddress() {
        Long id = 2L;
        Address addressMock = new Address(id, "Brazil", "MG", "BH", "Street", "00000-00");
        when(addressRepository.findById(eq(id)))
                .thenReturn(Optional.of(addressMock));

        Address response = addressService.findById(id);

        assertThat(response)
                .isNotNull()
                .matches(address -> id.equals(address.getId()))
                .matches(address -> "Brazil".equals(address.getCountry()))
                .matches(address -> "MG".equals(address.getState()))
                .matches(address -> "BH".equals(address.getCity()))
                .matches(address -> "Street".equals(address.getStreet()))
                .matches(address -> "00000-00".equals(address.getAddressCode()));
    }

    @Test
    void givenAddressId_whenFindById_thenResourceNotFoundException() {
        when(addressRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> addressService.findById(40L));
    }

    @Test
    void whenGetAll_thenListAddressVO() {
        Address addressMock1 = new Address(10L, "Brazil", "MG", "BH", "Street1", "00000-01");
        Address addressMock2 = new Address(11L, "Brazil", "MG", "BH", "Street2", "00000-02");
        Address addressMock3 = new Address(12L, "Brazil", "MG", "BH", "Street3", "00000-03");

        List<Address> addressesMock = Arrays.asList(addressMock1, addressMock2, addressMock3);

        when(addressRepository.findAll())
                .thenReturn(addressesMock);

        List<AddressVO> response = addressService.getAll();

        List<AddressVO> addressesVOMock = addressesMock.stream()
                .map(AddressVO::new).collect(Collectors.toList());

        assertArrayEquals(response.toArray(), addressesVOMock.toArray());
    }
}
