package com.revature.controller;

import com.revature.exception.ClientAccountNotFoundException;
import com.revature.model.Client;
import com.revature.model.ClientAccount;
import com.revature.service.ClientAccountService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ClientAccountController implements Controller {

    private ClientAccountService clientAccountService;

    public ClientAccountController(){
        this.clientAccountService = new ClientAccountService();
    }

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
       System.out.println(clientAccountToAdd);

    };
        //        try {

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


//
//    private Handler postTests = (ctx) -> {
//        ctx.html("<h1>post/tests<h1>");
//    };
//
//    private Handler putTests = (ctx) -> {
//        ctx.html("<h1>put/tests<h1>");
//    };
//
//    private Handler patchTests= (ctx) -> {
//        ctx.html("<h1>patch/tests<h1>");
//    };
//    private Handler deleteTests = (ctx) -> {
//        ctx.html("<h1>delete/tests<h1>");
//    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients/{clientIdInParam}/accounts", getAccountByClientId);
        app.post("/clients/{clientIdInParam}/accounts", addClientAccount);
//        app.put("/tests", putTests);
//        app.patch("/tests", patchTests);
//        app.delete("/tests", deleteTests);
    }
}
