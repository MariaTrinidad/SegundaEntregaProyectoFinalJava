package com.CODERHOUSE.SegundaPreEntregaGuerra.controller;

import com.CODERHOUSE.SegundaPreEntregaGuerra.middleware.ResponseHandler;
import com.CODERHOUSE.SegundaPreEntregaGuerra.model.Client;

import com.CODERHOUSE.SegundaPreEntregaGuerra.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/client")
public class ClientController {
@Autowired
    private ClientService clientService;

    //CRUD Cliente

    //Create

    @PostMapping
    public  ResponseEntity<Object> postClient (@RequestBody Client client){
        try {
            System.out.println(client);
            Client clientSaved = clientService.postClient(client);
            return ResponseHandler.generateResponse(
                    "Data retrieved successfully",
                    HttpStatus.OK,
                    clientSaved
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getClient (@PathVariable() int id) {
        try {
            System.out.println(id);
            Client clientFound = clientService.getClient(id);
            System.out.println(clientFound);
            System.out.println(clientFound.getInvoice());;

            return ResponseHandler.generateResponse(
                    "Client get successfully",
                    HttpStatus.OK,
                    clientFound
            );

        } catch (Exception e) {

            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }

    }

    @PutMapping
    public ResponseEntity<Object> updateClient(@RequestBody Client client){
        try {
            System.out.println("Cliente a actualizar" + client);
            clientService.updateClient(client);


            return ResponseHandler.generateResponse(
                    "Client Update successfully",
                    HttpStatus.OK,
                    client
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable() Integer id) {
        try {
            System.out.println("Cliente a borrar" + clientService.getClient(id));
            clientService.deleteClient(id);
            System.out.println(clientService.getClient(id));

            return ResponseHandler.generateResponse(
                    "Client delete successfully",
                    HttpStatus.OK,
                    clientService.getClient(id)
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }

    }
}
