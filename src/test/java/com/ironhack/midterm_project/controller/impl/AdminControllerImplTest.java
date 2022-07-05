package com.ironhack.midterm_project.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midterm_project.classes.Address;
import com.ironhack.midterm_project.classes.Money;
import com.ironhack.midterm_project.dto.AccountBalanceDTO;
import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.CreditCard;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.model.user.Admin;
import com.ironhack.midterm_project.model.user.Role;
import com.ironhack.midterm_project.model.user.User;
import com.ironhack.midterm_project.repository.AccountHolderRepository;
import com.ironhack.midterm_project.repository.AccountRepository;
import com.ironhack.midterm_project.repository.AdminRepository;
import com.ironhack.midterm_project.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerImplTest {

    @Autowired
    private MockMvc mockMvc; // Simular peticiones HTTP

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AccountRepository accountRepository;

    private AccountHolder accountHolder1, accountHolder2;
    private Role accountHolderRole1, accountHolderRole2;

    private Admin admin1, admin2;
    private Role adminRole1, adminRole2;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Hacer bodies

    @BeforeEach
    void setUp() {
        admin1 = new Admin("admin", passwordEncoder.encode("123456"));
        adminRole1 = new Role("ADMIN", admin1);
        admin1.setRoles(Set.of(adminRole1));

        admin2 = new Admin("admin", passwordEncoder.encode("123456"));
        adminRole2 = new Role("ADMIN", admin2);
        admin2.setRoles(Set.of(adminRole2));

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

        Account account = new Account(new Money(BigDecimal.valueOf(1250)), 1235, accountHolder1, accountHolder2, Status.ACTIVE);

        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));
       adminRepository.saveAll(List.of(admin1, admin2));
       accountRepository.save(account);

    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void findAccountById() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");

        // Validamos que el estatus de respuesta sea OK
        MvcResult mvcResult = mockMvc.perform(get("/AdminAccounts/1").headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("admin"));
    }

    @Test
    void findBalanceByAccountId() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");

        // Validamos que el estatus de respuesta sea OK
        MvcResult mvcResult = mockMvc.perform(get("/AdminAccounts/1/balance").headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1250"));
    }

    @Test
    void updateBalance() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO();
       accountBalanceDTO.setBalance(new Money(BigDecimal.valueOf(1500)));
        String body = objectMapper.writeValueAsString(accountBalanceDTO);
        // Validamos que el estatus de respuesta sea OK
        MvcResult mvcResult = mockMvc.perform(
                patch("/AdminAccounts/1/balance")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1500"));
    }

    @Test
    void createCheckingAccount() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        Checking checking = new Checking(new Money(BigDecimal.valueOf(1520)), 1250, accountHolder1,accountHolder2,Status.ACTIVE,
                new Date(2015/03/21));
        String body = objectMapper.writeValueAsString(checking);
        MvcResult mvcResult = mockMvc.perform(
                        post("/AdminAccounts/CheckingAccount")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1520"));
    }

    @Test
    void createSavingsAccount() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        Savings savings = new Savings(new Money(BigDecimal.valueOf(1520)), 1250, accountHolder1,accountHolder2,Status.ACTIVE,
                new Date(2015/03/21));
        String body = objectMapper.writeValueAsString(savings);
        MvcResult mvcResult = mockMvc.perform(
                        post("/AdminAccounts/SavingsAccount")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1520"));
    }

    @Test
    void createCreditCard() throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        CreditCard creditCard = new CreditCard( new Money(BigDecimal.valueOf(1520)), 1250, accountHolder1,
                accountHolder2,Status.ACTIVE, new Money(BigDecimal.valueOf(950)), BigDecimal.valueOf(0.2));
        String body = objectMapper.writeValueAsString(creditCard);
        MvcResult mvcResult = mockMvc.perform(
                        post("/AdminAccounts/CreditCard")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1520"));
    }

}