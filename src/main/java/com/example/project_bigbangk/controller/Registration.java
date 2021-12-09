package com.example.project_bigbangk.controller;
/*

@Author Bigbangk
*/

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.RegistrationDTO;
import com.example.project_bigbangk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class Registration {

    private RegistrationService registrationService;

    public Registration(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Receives http post request with JSON body and sends the DTO to registration service.
     * Then sents response based on what service returns
     * @param registrationDTO
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity receiveRegistrationInput(@RequestBody RegistrationDTO registrationDTO) {
        if(registrationService.registerClient(registrationDTO).equals("Registration Successful")){
        //stuur naar login, en popup?(voor later)
            return ResponseEntity.status(201).body("Registration Successful");
        }else if(registrationService.registerClient(registrationDTO).equals("Duplicate Email")) {
            return ResponseEntity.status(409).body("This e-mail is already in use");
        }else {
            return ResponseEntity.status(406).body("Bad Input");
        }
    }

}
