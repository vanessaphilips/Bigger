package com.example.project_bigbangk.service;


import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.repository.RootRepository;
import com.example.project_bigbangk.service.Security.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Pieter Jan Bleichrodt
 */
class AuthenticateServiceTest {

        //Deze testen zijn meer een oefening in Mockito en het isoleren van de service, de test zelf voegt niet zo veel toe
        @Test
        void authenticateSucces() {
            ITokenService tokenService = Mockito.mock(JWTService.class);
            RootRepository rootRepository = Mockito.mock(RootRepository.class);
            IHashService hashService = Mockito.mock(HashService.class);
            AuthenticateService authenticateService = new AuthenticateService(tokenService, rootRepository, hashService);
            Client client = Mockito.mock(Client.class);
            Mockito.when(client.getPassWord()).thenReturn("DeekPW");
            Mockito.when(hashService.hashCheck("DeekPW", client.getPassWord())).thenReturn(true);
            Mockito.when(rootRepository.findClientByEmail("deek@deek.nl")).thenReturn(client);
            assertTrue(authenticateService.authenticate("deek@deek.nl" ,"DeekPW"));
        }
}