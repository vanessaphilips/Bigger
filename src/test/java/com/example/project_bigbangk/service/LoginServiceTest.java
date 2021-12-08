package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by Pieter Jan Bleichrodt
 */
class LoginServiceTest {

    static ITokenService tokenService = Mockito.mock(JWTService.class);
    static ClientService clientService = Mockito.mock(ClientService.class);
    static AuthenticateService authenticateService = Mockito.mock(AuthenticateService.class);
    static Client client = Mockito.mock(Client.class);
    static LoginService loginService;
    static final String EMAIL = "deek@deek.nl";
    static final String PASSWORD = "DeekPW";

    @BeforeAll
    public static void setup() {       Mockito.when(client.getFirstName()).thenReturn("Deek");
        Mockito.when(client.getEmail()).thenReturn("deek@deek.nl");
        Mockito.when(clientService.getClientByEmail(EMAIL)).thenReturn(client);
        Mockito.when(tokenService.getToken(EMAIL, client.getFirstName())).thenReturn("succes");
        loginService = new LoginService(authenticateService, tokenService, clientService);
    }

    @Test
    public void loginSucces() {
        Mockito.when(authenticateService.authenticate(EMAIL, PASSWORD)).thenReturn(true);
        String expected = "succes";
        String actual = loginService.login("deek@deek.nl", "DeekPW");
        assertEquals(expected, actual);
    }
    @Test
    public void loginUnSuccesFull() {
        Mockito.when(authenticateService.authenticate(EMAIL, PASSWORD)).thenReturn(false);
        String expected = null;
        String actual = loginService.login("deek@deek.nl", "DeekPW");
        assertEquals(expected, actual);
    }
}