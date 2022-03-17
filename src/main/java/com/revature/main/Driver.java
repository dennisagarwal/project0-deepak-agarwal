package com.revature.main;

import com.revature.controller.*;
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
//        try {
//            List<ClientAccount> clientAccounts = clientAccountDao.getAccountsByClientIdBalance(10,200,10000);
//            System.out.println(clientAccountDao.getAccountsByClientIdBalance(13,200000,100));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Javalin app = Javalin.create();

        //this will execute before every single request
        app.before((ctx) -> {
            logger.info(ctx.method() + "request received for" + ctx.path());
        });
        //calling the constructor here for creating the new Client Controller object to be created
        //passing the object in line 28
        mapControllers(app, new HelloWorldController(), new ClientController(), new ExceptionController(), new ClientAccountController());

        app.start(8081); //port 8080 by default
    }
    //we create controller interface

    public static void mapControllers(Javalin app, Controller... controllers) {
        //loop through each of the controller and called map endpoint on them
        for (Controller c : controllers) {
            //call map points on controller and passing in the server object as app to controller
            c.mapEndpoints(app);
        }

    }
}
