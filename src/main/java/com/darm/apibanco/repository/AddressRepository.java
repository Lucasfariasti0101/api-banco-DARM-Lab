package com.darm.apibanco.repository;

import com.darm.apibanco.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
