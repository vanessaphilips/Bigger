package com.example.project_bigbangk.service.Security;


import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class can authorize an login email and password combination and authorize a token
 * created by Pieter Jan Bleichrodt
 * Creation date 12/3/2021
 */
@Service
public class AuthenticateService {
    RootRepository rootRepository;
    ITokenService tokenService;
    IHashService hashService;

    @Autowired
    public AuthenticateService(ITokenService tokenService, RootRepository rootRepository, IHashService hashService) {
        super();
        this.rootRepository = rootRepository;
        this.tokenService = tokenService;
        this.hashService = hashService;
    }
    /**
     * Authenticate the login credentials which you got from the clientside in an request
     * @param email
     * @return true if the combination is valid
     */
    public boolean authenticate(String email, String password) {
        Client client = rootRepository.findClientByEmail(email);
        if (client != null) {
            return hashService.hashCheck(password, client.getPassWord());
        }
        return false;
    }

    /**
     * Authenticate your token which you got from the clientside in an request
     * @param token the token you  get from the client
     * @return true if the token is valid
     */
    public boolean authenticate(String token) {
        return tokenService.authenticateToken(removeBearer(token));
    }

    /**
     * Get the Client which belongs to the request you get from clientside by sending the corresponding token
     * @param token the token you  get from the client
     * @return
     */
    public Client getClientFromToken(String token){
        String email =   tokenService.getEmailFromToken(removeBearer(token));
        Client client = rootRepository.findClientByEmail(email);
        return client;
    }

    private String removeBearer(String token){
       return token.replace("Bearer ", "");
    }
}