package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.ProjectBigBangKApplication;
import com.example.project_bigbangk.model.LoginDTO;
import com.example.project_bigbangk.service.ClientService;
import com.example.project_bigbangk.service.LoginService;
import com.example.project_bigbangk.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.MessageDigest;

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
        LoginDTO loginDTO = new LoginDTO("deek", "password12345");
        this.builder = MockMvcRequestBuilders.post("/login")
                .content(asJsonString(loginDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

    }


    @Test
    void login() {
        Mockito.when(loginService.login("deek", "password12345")).thenReturn(null);
        ResultActions response;
        try {
            response = mockMvc.perform(builder);
            MvcResult result = response.andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andReturn();
            assertEquals("Username or password not valid", result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void login2() {
        String token = "deek";
        Mockito.when(loginService.login("deek", "password12345")).thenReturn(token);
        ResultActions response;

        try {
            response = mockMvc.perform(builder);
            MvcResult result = response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            assertEquals("deek", result.getResponse().getContentAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}