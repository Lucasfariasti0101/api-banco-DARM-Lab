package com.darm.apibanco.repository;

import com.darm.apibanco.model.UserChangePassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChangePasswordRepository extends JpaRepository<UserChangePassword, Long> {

    Optional<UserChangePassword> findByCodeAndEmail(String code, String email);

}
