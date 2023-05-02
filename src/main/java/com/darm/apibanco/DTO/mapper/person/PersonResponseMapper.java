package com.darm.apibanco.DTO.mapper.person;

import com.darm.apibanco.DTO.PersonResponse;
import com.darm.apibanco.model.Person;
import com.darm.apibanco.shared.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PersonResponseMapper implements Mapper<Person, PersonResponse> {
    @Override
    public PersonResponse map(Person source) {
        return new PersonResponse(source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getCpf(),
                source.getPhoneNumber(), source.getAddress());
    }
}
