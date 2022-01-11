package com.example.project_bigbangk.controller;
/*
@Author Philip Beeltje, Studentnummer: 500519452
*/

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.DTO.OrderDTO;
import com.example.project_bigbangk.service.Orderservice;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class OrderController{

    private Orderservice orderservice;
    private AuthenticateService authenticateService;

    public OrderController(Orderservice orderservice, AuthenticateService authenticateService){
        super();
        this.orderservice = orderservice;
        this.authenticateService = authenticateService;
    }

    //als je vanuit marketplace of je portfolio op order knop drukt bij coin x, kom je hier aan.
    @PostMapping("/orderscreen")
    public ResponseEntity<String> gotoOrderScreen(){
        //@Requestbody jason -> dto? of alleen coin x code in de body kan ook goed.
        //ga naar orderpagina voor coin x <- eigelijk het enige dat deze method doet.
        // (kan misschien deze methode overslaan en in portfolio en market service doen maar minder netjes)

        //die html pagina doet het volgende:
        //klant voert orderinfo in
        //check of orderinfo ok is (niet of de order kan maar gewoon voor onmogelijke dingen)
        //stuur orderinfo in JSON vorm naar /placeorder...zie onder
        return null;
    }

    //in het scherm hierboven^ kan je alles van je order instellen, dat wordt hieronder opgevangen.
    @PostMapping("/placeorder")

    public ResponseEntity<String> placeOrder(@RequestHeader String authorization, @RequestBody OrderDTO orderDTO) {
        if (authenticateService.authenticate(authorization)) {
            Client client = authenticateService.getClientFromToken(authorization);
            String orderMessage = orderservice.executeOrderByType(orderDTO, client);
            if (orderMessage == null) {
                return ResponseEntity.status(406).body("Order Failed.");
            } else {
                return ResponseEntity.status(201).body(orderMessage);
            }
        }
        return ResponseEntity.status(401).body("token expired");
    }




}
