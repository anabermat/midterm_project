package com.ironhack.midterm_project.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midterm_project.model.user.Admin;
import com.ironhack.midterm_project.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminControllerImplTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private WebApplicationContext webApplicationContext; // Nos va a permitir trabajar con los controllers
    private MockMvc mockMvc; // Simular peticiones HTTP
    private final ObjectMapper objectMapper = new ObjectMapper(); // Contruir Objetos JSON a partir de clase de JAVA

    private Admin admin1, admin2;

    @BeforeEach
    void setUp() {
        // Initiacializar el MockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Guardamos la data por defecto
        admin1 = new Admin("ana", "Intro to Java");
        admin2 = new Admin("CS103", "Databases");
        adminRepository.saveAll(List.of(admin1, admin2));
    }

    @AfterEach
    void tearDown() {
        adminRepository.deleteAll();
    }

    @Test
    void findAccountById() {

    }

    @Test
    void findBalanceByAccountId() {

    }

    @Test
    void updateBalance() {

    }

    @Test
    void createCheckingAccount() {

    }

    @Test
    void createSavingsAccount() {

    }

    @Test
    void createCreditCard() {

    }

    @Test
    void delete() {
        
    }
}