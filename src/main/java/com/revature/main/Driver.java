package com.revature.main;

import com.revature.controller.Controller;
import com.revature.dao.ClientDao;
import com.revature.model.Client;
import com.revature.utility.ConnectionUtility;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) {
        //        ClientDao clientDao = new ClientDao();
        //this willl give us the Javalin object
        //the javalin object is the server
        Javalin app = Javalin.create();

        mapControllers(app);

        app.start(); //port 8080 by default
    }
//we create controller interface

    public static void mapControllers(Javalin app, Controller... controllers ) {
        //loop through each of the controller and called map endpoint on them
       for(Controller c : controllers){
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
