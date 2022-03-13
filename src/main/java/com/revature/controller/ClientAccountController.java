package com.revature.controller;

import com.revature.exception.ClientAccountNotFoundException;
import com.revature.model.Client;
import com.revature.model.ClientAccount;
import com.revature.service.ClientAccountService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ClientAccountController implements Controller {

    private ClientAccountService clientAccountService;

    public ClientAccountController(){
        this.clientAccountService = new ClientAccountService();
    }

//    private Handler getAllClientAccounts = (ctx) -> {
//        List<ClientAccount> clients = clientAccountService.getAllClientAccounts();
//        //calling the clients
//        ctx.json(clients);
//    };

    private Handler getAccountByClientIdAccountId = (ctx) -> {
        String id1 = ctx.pathParam("clientId");
        String id2 = ctx.pathParam("accountId");
        try{
            ClientAccount clientAccount = clientAccountService.getAccountByClientIdAccountId(id1,id2);
            ctx.json(clientAccount);
        } catch(ClientAccountNotFoundException e){
            ctx.status(404);
            ctx.json(e.getMessage());
        }
    };

    private Handler getAccountByClientId = (ctx) -> {
        String id = ctx.pathParam("clientIdInParam");

        try{
            ClientAccount clientAccount = clientAccountService.getAccountByClientId(id);
            ctx.json(clientAccount);
        }catch(ClientAccountNotFoundException e){
            ctx.status(404);
            ctx.json(e.getMessage());
        }
    };

    private Handler addClientAccount = (ctx) ->{
       ClientAccount clientAccountToAdd = ctx.bodyAsClass(ClientAccount.class);
//       System.out.println(clientAccountToAdd);
        ClientAccount ca = clientAccountService.addClientAccount(clientAccountToAdd);
        ctx.status(201);
        ctx.json(ca);

    };

    private Handler editClientAccount = (ctx) ->{

        ClientAccount clientAccountToEdit = ctx.bodyAsClass(ClientAccount.class);

       ClientAccount editedClientAccount= clientAccountService.editClientAccount(ctx.pathParam("clientIdInParam"),
               clientAccountToEdit);
       ctx.status(200);
        ctx.json(editedClientAccount);
    };

    private Handler deleteClientAccountById = (ctx) -> {
        String id2 = ctx.pathParam("accountIdInParam");
        String id1 = ctx.pathParam("clientIdInParam");
        boolean clientAccount = clientAccountService.deleteAccountOfClient(id1,id2);

        ctx.json(clientAccount);

    };


    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients/{clientIdInParam}/accounts", getAccountByClientId);
        app.get("/clients/{clientId}/accounts/{accountId}", getAccountByClientIdAccountId);
        app.post("/clients/{clientIdInParam}/accounts", addClientAccount);
        app.put("/clients/{clientIdInParam}/accounts/{accountIdInParam}", editClientAccount);
        app.delete("/clients/{clientIdInParam}/accounts/{accountIdInParam}", deleteClientAccountById);
    }
}
