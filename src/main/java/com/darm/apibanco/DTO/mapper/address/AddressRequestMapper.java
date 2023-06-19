package com.darm.apibanco.DTO.mapper.address;

import com.darm.apibanco.DTO.AddressRequest;
import com.darm.apibanco.model.Address;
import com.darm.apibanco.shared.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressRequestMapper implements Mapper<AddressRequest, Address> {

    @Override
    public Address map(AddressRequest source) {
        Address address = new Address();
        address.setStreet(source.street());
        address.setState(source.state());
        address.setMunicipality(source.municipality());
        address.setNumber(source.number());
        address.setComplement(source.complement());
        address.setNeighborhood(source.neighborhood());
        return address;
    }

}
