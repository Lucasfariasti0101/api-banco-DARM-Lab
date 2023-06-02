package com.darm.apibanco.service;

import com.darm.apibanco.DTO.*;
import com.darm.apibanco.exception.ConflictException;
import com.darm.apibanco.model.Person;
import com.darm.apibanco.model.User;
import com.darm.apibanco.model.enums.Role;
import com.darm.apibanco.repository.PersonRepository;
import com.darm.apibanco.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    private final PersonService personService;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authManager, PersonService personService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.personService = personService;
    }

    @Transactional
    public AuthenticationResponse register(RegisterUserRequest request, Role role) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("This email is already being used.");
        }

        Person person = createPerson(request.personRequest());

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .person(person)
                .role(role)
                .build();

        person.setUser(user);
        userRepository.save(user);
        personService.save(person);
        String token = jwtService.generateToken(user);

        return createRegisterResponse(token, person);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));

        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                  request.email(),
                  request.password()));


        String token = jwtService.generateToken(user);
        return createRegisterResponse(token, user.getPerson());
    }

    private Person createPerson(RegisterPersonRequest request) {

        return Person.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .cpf(request.cpf())
                .phoneNumber(request.phoneNumber())
                .build();
    }

    private AuthenticationResponse createRegisterResponse(String token, Person person) {
        PersonResponse personResponse = new PersonResponse(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getCpf(),
                person.getPhoneNumber(),
                person.getAddress(),
                person.getAccount().getNumber(),
                person.getAccount().getType().name());

        return new AuthenticationResponse(token, personResponse);

    }
}
