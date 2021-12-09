package com.example.project_bigbangk.controller;
/**

@Author Bigbangk
*/

import com.example.project_bigbangk.model.DTO.RegistrationDTO;
import com.example.project_bigbangk.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Receives http post request with JSON body and sends the DTO to registration service.
     * Then sends response based on what service returns
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
