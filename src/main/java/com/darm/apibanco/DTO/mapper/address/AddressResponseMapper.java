package com.darm.apibanco.DTO.mapper.address;

import com.darm.apibanco.DTO.AddressResponse;
import com.darm.apibanco.model.Address;
import com.darm.apibanco.shared.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressResponseMapper implements Mapper<Address, AddressResponse> {

    @Override
    public AddressResponse map(Address source) {
        return new AddressResponse(
                source.getMunicipality(),
                source.getStreet(),
                source.getNumber(),
                source.getComplement(),
                source.getNeighborhood(),
                source.getState(),
                source.getPerson().getFirstName(),
                source.getPerson().getId());
    }

}
