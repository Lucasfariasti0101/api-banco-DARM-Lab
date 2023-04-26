package com.darm.apibanco.DTO;

import com.darm.apibanco.model.Address;

import java.util.List;

public record PersonResponse(
        Long id,
        String firstName,
        String lastName,
        String cpf,
        String phoneNumber,
        List<Address> address
) {
}
