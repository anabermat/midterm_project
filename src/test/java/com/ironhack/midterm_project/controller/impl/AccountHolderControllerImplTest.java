package com.ironhack.midterm_project.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midterm_project.classes.Address;
import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.model.user.Admin;
import com.ironhack.midterm_project.model.user.Role;
import com.ironhack.midterm_project.repository.AccountHolderRepository;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountHolderControllerImplTest {

    @Autowired
    private MockMvc mockMvc; // Simular peticiones HTTP

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AccountRepository accountRepository;

    private AccountHolder accountHolder1, accountHolder2;
    private Role accountHolderRole1, accountHolderRole2;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        Address primaryAddress1 = new Address("Madrid", "Calle Zarzalones", 28400);
        Address primaryAddress2 = new Address("Barcelona", "Avenida La Virgen", 20583);

        Address mailingAddress1 = new Address("Madrid", "Calle Zarzalones", 28400);
        Address mailingAddress2 = new Address("Barcelona", "Avenida La Virgen", 20583);



//        String username, String password, Date dateOfBirth, Address primaryAddress, Address mailingAddress
        accountHolder1 = new AccountHolder("accountHolder", passwordEncoder.encode("123456"), new Date(1954/06/11),
                primaryAddress1, mailingAddress1);
        accountHolderRole1 = new Role("ACCOUNT HOLDER", accountHolder1);
        accountHolder1.setRoles(Set.of(accountHolderRole1));

        accountHolder2 = new AccountHolder("accountHolder", passwordEncoder.encode("123456"), new Date(1963/10/27),
                primaryAddress2, mailingAddress2);
        accountHolderRole2 = new Role("ACCOUNT HOLDER", accountHolder2);
        accountHolder2.setRoles(Set.of(accountHolderRole2));

        //Money balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
//                Status status

        Account account = new Account(new Money(BigDecimal.valueOf(1250)), 1235, accountHolder1, accountHolder2, Status.ACTIVE);

        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2));
        accountRepository.save(account);
    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void findAccountById() throws Exception{

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWNjb3VudEhvbGRlcjoxMjM0NTY=");

        // Validamos que el estatus de respuesta sea OK
        MvcResult mvcResult = mockMvc.perform(get("/AccountHolderAccounts/1").headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("accountHolder"));

    }

    @Test
    void findBalanceByAccountId() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWNjb3VudEhvbGRlcjoxMjM0NTY=");

        // Validamos que el estatus de respuesta sea OK
        MvcResult mvcResult = mockMvc.perform(get("/AccountHolderAccounts/1/balance").headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1250"));
    }

    @Test
    void transferMoney() throws Exception{

    }
}