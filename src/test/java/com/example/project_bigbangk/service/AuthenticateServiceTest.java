package com.example.project_bigbangk.service;


import com.example.project_bigbangk.model.Client;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


/**
 * created by Pieter Jan Bleichrodt
 */
class AuthenticateServiceTest {

        //Deze testen zijn meer een oefening in Mockito en het isoleren van de service, de test zelf voegt niet zo veel toe
        @Test
        void authenticateSucces() {
            ITokenService tokenService = Mockito.mock(JWTService.class);
            ClientService clientService= Mockito.mock(ClientService.class);
            IHashService hashService = Mockito.mock(HashService.class);
            AuthenticateService authenticateService = new AuthenticateService(tokenService, clientService, hashService);
            Client client = Mockito.mock(Client.class);
            Mockito.when(client.getPassWord()).thenReturn("DeekPW");
            Mockito.when(hashService.hashCheck("DeekPW", client.getPassWord())).thenReturn(true);
            Mockito.when(clientService.getClientByEmail("deek@deek.nl")).thenReturn(client);
            assertTrue(authenticateService.authenticate("deek@deek.nl" ,"DeekPW"));
        }
}