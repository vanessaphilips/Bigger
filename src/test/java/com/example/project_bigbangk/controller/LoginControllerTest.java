package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.ProjectBigBangKApplication;
import com.example.project_bigbangk.service.ClientService;
import com.example.project_bigbangk.service.LoginService;
import com.example.project_bigbangk.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
//@SpringBootTest(classes = ProjectBigBangKApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@TestPropertySource(properties =
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
class LoginControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private ClientService clientService;

    @MockBean
    private RegistrationService registrationService;
    MockHttpServletRequestBuilder builder;

    @Autowired
    public LoginControllerTest(MockMvc mockMvc) {
        super();
        this.mockMvc = mockMvc;
        this.builder = MockMvcRequestBuilders.post("/login")
                .param("email", "deek")
                .param("password", "password12345");
    }


    @Test
    void login() {
        Mockito.when(loginService.login("deek", "password12345")).thenReturn(null);
        ResultActions response = null;
        try {
            response = mockMvc.perform(builder);
            response.andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void login2() {
        Mockito.when(loginService.login("deek", "password12345")).thenReturn("deek");
        ResultActions response = null;

        try {
            response = mockMvc.perform(builder);
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}