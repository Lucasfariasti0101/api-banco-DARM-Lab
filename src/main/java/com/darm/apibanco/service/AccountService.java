package com.darm.apibanco.service;

import com.darm.apibanco.DTO.AccountRequest;
import com.darm.apibanco.exception.BadRequestException;
import com.darm.apibanco.exception.PersonNotFoundException;
import com.darm.apibanco.exception.ResourceNotFoundException;
import com.darm.apibanco.model.Account;
import com.darm.apibanco.model.Person;
import com.darm.apibanco.model.enums.AccountType;
import com.darm.apibanco.repository.AccountRepository;
import com.darm.apibanco.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    public AccountService(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    public Account findAccountByPerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);
        if (person.getAccount() == null) {
            throw new ResourceNotFoundException("Person does not have a bank account");
        }
        return person.getAccount();
    }
    @Transactional
    public Account save(Long personId, AccountRequest request) {

        Person person = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);

        Account account = new Account();
        account.setType(convertStringInAccountType(request.accountType()));
        account.setNumber(generateNumber(8));
        account.setPerson(person);

        Account accountSaved = accountRepository.save(account);
        person.setAccount(accountSaved);
        personRepository.save(person);

        return accountSaved;
    }

    @Transactional
    public void updateAccountType(Long personId, AccountRequest request) {
        Account account = findAccountByPerson(personId);
        account.setType(convertStringInAccountType(request.accountType()));

        accountRepository.save(account);
    }

    private AccountType convertStringInAccountType(String text) {
        return Arrays.stream(AccountType.values())
                .filter(accountType -> accountType.equals(AccountType.valueOf(text.toUpperCase())))
                .findAny()
                .orElseThrow(() -> new BadRequestException("Non-existent type"));

    }

    private String generateNumber(int length) {
        String CHARSET = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (i == 6) {
                sb.append("-");
            } else {
                int index = random.nextInt(CHARSET.length());
                sb.append(CHARSET.charAt(index));
            }
        }
        boolean existsAccount = accountRepository.existsByNumber(sb.toString());
        if (existsAccount) {
            generateNumber(length);
        }
        return sb.toString();
    }
}
