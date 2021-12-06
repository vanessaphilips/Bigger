package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest
class RegistrationTest {
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    public RegistrationTest(MockMvc mockMvc){
        super();
        this.mockMvc = mockMvc;
    }


    @Test
    public void testUserController () throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/register")
                .param("email", "philip.beeltje@gmail.com")
                .param("password", "password12345")
                .param("firstName", "Philip")
                .param("insertion", "")
                .param("lastName", "Beeltje")
                .param("bsn", "123456789")
                .param("dateOfBirth", "1986-01-07")
                .param("postalCode", "1241HL")
                .param("street", "HuisjesSteeg")
                .param("number", "8")
                .param("city", "Amstelredam")
                .param("country", "NLD");

        ResultActions response = mockMvc.perform(builder);
        response.andExpect(MockMvcResultMatchers.status().isOk()); //heb nog geen response
    }



}