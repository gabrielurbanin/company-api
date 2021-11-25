package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.AddressForm;
import br.com.hotmart.java.controllers.vo.AddressVO;
import br.com.hotmart.java.entities.Address;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.hotmart.java.builders.AddressBuilder.buildAddress;
import static br.com.hotmart.java.builders.AddressFormBuilder.buildAddressForm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @Spy
    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void whenGetAll_thenListAddressVO() {
        List<Address> addressesMock = Arrays
                .asList(buildAddress("street1"), buildAddress("street2"), buildAddress("street3"));

        when(addressRepository.findAll())
                .thenReturn(addressesMock);

        List<AddressVO> response = addressService.getAll();

        List<AddressVO> addressesVOMock = addressesMock.stream()
                .map(AddressVO::new).collect(Collectors.toList());

        assertArrayEquals(response.toArray(), addressesVOMock.toArray());
    }

    @Test
    void givenAddressForm_whenSave_thenCallSaveFromAddressRepository() {
        Address address = buildAddress("street");
        AddressForm addressForm = buildAddressForm("street");

        when(addressRepository.save(any(Address.class)))
                .thenReturn(address);

        addressService.save(addressForm);

        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void givenAddressId_whenFindById_thenAddress() {
        Long id = 2L;
        Address addressMock = buildAddress("street");
        when(addressRepository.findById(eq(id)))
                .thenReturn(Optional.of(addressMock));

        Address response = addressService.findById(id);

        assertThat(response)
                .isNotNull()
                .matches(address -> addressMock.getId().equals(address.getId()))
                .matches(address -> addressMock.getCountry().equals(address.getCountry()))
                .matches(address -> addressMock.getState().equals(address.getState()))
                .matches(address -> addressMock.getCity().equals(address.getCity()))
                .matches(address -> addressMock.getStreet().equals(address.getStreet()))
                .matches(address -> addressMock.getAddressCode().equals(address.getAddressCode()));
    }

    @Test
    void givenAddressId_whenFindById_thenResourceNotFoundException() {
        when(addressRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> addressService.findById(40L));
    }

    @Test
    void givenAddressId_whenGetById_thenCallFindByIdFromAddressService() {
        Address address = buildAddress("street");

        when(addressRepository.findById(1L))
                .thenReturn(Optional.of(address));

        addressService.getById(1L);

        verify(addressService, times(1)).findById(1L);
    }

    @Test
    void givenAddressIdAndAddressForm_whenUpdate_thenAddressVO() {
        Address address = buildAddress("street");
        AddressForm addressForm = buildAddressForm("streetUpdated");

        when(addressRepository.findById(1L))
                .thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class)))
                .thenReturn(address);

        ArgumentCaptor<Address> addressCaptor = ArgumentCaptor.forClass(Address.class);

        addressService.update(1L, addressForm);

        verify(addressRepository, times(1)).save(addressCaptor.capture());

        assertThat(addressCaptor.getValue())
                .isNotNull()
                .matches(addressSaved -> addressForm.getStreet().equals(addressSaved.getStreet()));
    }

    @Test
    void givenAddressId_whenDelete_thenCallDeleteFromAddressRepository() {
        Address address = buildAddress("street");

        when(addressRepository.findById(1L))
                .thenReturn(Optional.of(address));

        addressService.delete(1L);

        verify(addressRepository, times(1)).delete(any(Address.class));
    }



}
