package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.Utilities.ObjectToJsonHelper;
import com.example.project_bigbangk.model.RegistrationDTO;
import com.example.project_bigbangk.service.ClientService;
import com.example.project_bigbangk.service.LoginService;
import com.example.project_bigbangk.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest
@TestPropertySource(properties =
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
class RegistrationTest {


    private MockMvc mockMvc;
    private MockHttpServletRequestBuilder builder;
    private RegistrationDTO registrationDTO;

    @MockBean
    private LoginService loginService;

    @MockBean
    private ClientService clientService;

    @MockBean
    private RegistrationService registrationService;

    @Autowired
    public RegistrationTest(MockMvc mockMvc){
        super();
        this.mockMvc = mockMvc;
    }

    @Test
    void RegisterClient() throws Exception {

        registrationDTO = new RegistrationDTO("henk@unicom.nl", "password1234345", "Henk", "de", "Kort",
                "123434546", "1950-01-01", "1111BN", "Straatie", 9, "Muiden", "NLD");

        Mockito.when(registrationService.registerClient(registrationDTO)).thenReturn("Registration Successful");

        System.out.println(registrationService.registerClient(registrationDTO));
        System.out.println(ObjectToJsonHelper.objectToJson(registrationDTO));

        builder = MockMvcRequestBuilders.post("http://localhost:8080/register")
                .content(ObjectToJsonHelper.objectToJson(registrationDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        ////Cannot invoke "String.equals(Object)" because the return value of "com.example.project_bigbangk.service.RegistrationService.registerClient(com.example.project_bigbangk.model.RegistrationDTO)" is null
        ////WAAROM HIJ IS HELEMAAL NIET NULL, mockito returned een string

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}