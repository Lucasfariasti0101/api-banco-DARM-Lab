package com.darm.apibanco.shared;

import com.darm.apibanco.DTO.AddressRequest;
import com.darm.apibanco.model.Address;

public interface Mapper<S, T> {
    T map(S source);

    Address map(AddressRequest source);
}
