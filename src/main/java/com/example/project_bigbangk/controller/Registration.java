package com.example.project_bigbangk.controller;
/*

@Author Bigbangk
*/

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.RegistrationDTO;
import com.example.project_bigbangk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/client")
public class Registration {

    private RegistrationService registrationService;


    public Registration(RegistrationService registrationService) {
        this.registrationService = registrationService;
        //als dit lukt response sturen
    }

    @PostMapping("/register")
    @ResponseBody
    public void receiveRegistrationInput( @RequestBody RegistrationDTO registrationDTO) {
        //krijgt een error als de inkomende data niet voldoet aan het object, wat als verkeerde data toch door frontend komt?
        registrationService.registerClient(registrationDTO);
    }

}
