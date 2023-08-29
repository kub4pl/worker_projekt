package com.example.workerapplication.core.controller;

import com.example.workerapplication.dto.AddressDto;
import com.example.workerapplication.model.Address;
import com.example.workerapplication.repository.AddressRepository;
import com.example.workerapplication.validators.PostalCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/address")
    public AddressDto createAddress(@RequestBody AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setVoivodeship(addressDto.getVoivodeship());

        if (!PostalCodeValidator.isValidPostalCode(addressDto.getZipCode())) {
            throw new RuntimeException(" ZIPCODE is not correct ");
        }
        address.setZipCode(addressDto.getZipCode());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setBuildingNumber(addressDto.getBuildingNumber());
        addressRepository.save(address);
        return addressDto;
    }
}
