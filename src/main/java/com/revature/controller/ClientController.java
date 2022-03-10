package com.revature.controller;

import com.revature.model.Client;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ClientController implements Controller {

    private ClientService clientService;

    public ClientController(){
        this.clientService = new ClientService();
    }
//this lambda will implicitly have "throws exception" based on the functional interface
    //this is something to be aware of, because you might want to actually handle some exceptions
    private Handler getAllClients = (ctx) -> {
        List<Client> clients = clientService.getAllClients();
    };

    @Override
    public void mapEndpoints(Javalin app) {
     app.get("/clients", getAllClients);
    }
}
