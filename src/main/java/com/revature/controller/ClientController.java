package com.revature.controller;

import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ClientController implements Controller {

    private ClientService clientService;
    //this lambda will implicitly have "throws exception" based on the functional interface
    //this is something to be aware of, because you might want to actually handle some exceptions
    private Handler getAllClients = (ctx) -> {
        List<Client> clients = clientService.getAllClients();
        //calling the clients
        ctx.json(clients);
    };
    private Handler getClientById = (ctx) -> {
        String id = ctx.pathParam("clientId");
//        try {
        Client client = clientService.getClientById(id);

        ctx.json(client);
        //as we are handling all the exception from exception controller
//        }
//        catch (ClientNotFoundException e) {
//            ctx.status(404); //404 not found status code
//            ctx.json(e.getMessage()); //send back the custom exception message
//        }
//        catch (IllegalArgumentException e) {
//            ctx.status(400);
//            ctx.json(e.getMessage());
//        }

    };

    public ClientController() {
        this.clientService = new ClientService();
    }

// extending a runtime exception is unchecked exception
    //create checked exception by extending exception

    private Handler addClient = (ctx)->{
        Client clientToAdd = ctx.bodyAsClass(Client.class);

        Client c = clientService.addClient(clientToAdd);
        ctx.status(201); // 201 Created
        ctx.json(c);
        //System.out.println(clientToAdd);
    };

    private Handler editClient = (ctx) -> {
        Client clientToEdit = ctx.bodyAsClass(Client.class);
        Client editedClient = clientService.editClient(ctx.pathParam("clientId"),clientToEdit);
        ctx.status(200);
        ctx.json(editedClient);
    };

    private Handler deleteClientById = (ctx) -> {
        String id = ctx.pathParam("clientId");
        boolean client = clientService.deleteClientById(id);

        ctx.json(client);

    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients", getAllClients);
        //{clientId} is a pat variable
        //if error 404 not check the end points
        app.get("/clients/{clientId}", getClientById);
        app.post("/clients", addClient);
        app.put("/clients/{clientId}", editClient);
        app.delete("/clients/{clientId}", deleteClientById);
    }
}
