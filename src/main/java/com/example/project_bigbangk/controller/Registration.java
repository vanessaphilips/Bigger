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
@RequestMapping(value = "/client")
public class Registration {

    private RegistrationService registrationService;

    public Registration(RegistrationService registrationService) {
        this.registrationService = registrationService;

    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity receiveRegistrationInput(@RequestBody RegistrationDTO registrationDTO) {
        if(registrationService.registerClient(registrationDTO)){
            //stuur naar login, en popup?(voor later)
            return ResponseEntity.ok(HttpStatus.OK);
        }else{
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

}
