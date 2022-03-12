package com.revature.main;

import com.revature.controller.ClientController;
import com.revature.controller.ExceptionController;
import com.revature.controller.HelloWorldController;
import com.revature.controller.Controller;
import com.revature.dao.AccountDao;
import com.revature.dao.ClientAccountDao;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.model.ClientAccount;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;


public class Driver {

    public static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {

        ClientAccountDao clientAccountDao = new ClientAccountDao();
        try {
            List<ClientAccount> clientAccounts = clientAccountDao.getAllClientAccounts();

//            for(ClientAccount ca : clientAccounts){
//                System.out.println(ca);
//            }
//            System.out.println(clientAccounts);
            ClientAccount ca1 = clientAccountDao.getAccountByClientId(10);
            System.out.println("ca1 " + ca1);

//            ClientAccount ca2 = clientAccountDao.getAccountByClientId(10);
//            System.out.println("ca2 " + ca2);


        } catch (SQLException e) {
            e.printStackTrace();
        }

//        AccountDao accountDao = new AccountDao();
//        try {
//            List<Account> accounts = accountDao.getAllAccounts();
//            System.out.println(accounts);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //ClientDao clientDao = new ClientDao();
        //this willl give us the Javalin object
        //the javalin object is the server
        Javalin app = Javalin.create();

        //this will execute before every single request
        app.before((ctx) -> {
            logger.info(ctx.method() + "request received for" + ctx.path());
        });
//calling the constructor here for creating the new Client Controller object to be created
        //passing the object in line 28
        mapControllers(app, new HelloWorldController(), new ClientController(), new ExceptionController());

        app.start(8081); //port 8080 by default
    }
//we create controller interface

    public static void mapControllers(Javalin app, Controller... controllers) {
        //loop through each of the controller and called map endpoint on them
        for (Controller c : controllers) {
            //call map points on controller and passing in the server object as app to controller
            c.mapEndpoints(app);
        }

//        try{
        //            Client c = new Client(5, "Rad", "Doe", 31);
//            System.out.println(clientDao.updateClient(c));
//            System.out.println(clientDao.addClient(c));
//
//            System.out.println(clientDao.getClientById(1));
//
//            clientDao.deleteClientById(3);
//            System.out.println(clientDao.deleteClientById(3));
//            System.out.println(clientDao.getAllClients());
//            System.out.println(clientDao.getAllClients());
//        } catch (SQLException e){
//            e.printStackTrace();
//        }

    }
}
