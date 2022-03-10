package com.revature.main;

import com.revature.controller.ClientController;
import com.revature.controller.ExceptionController;
import com.revature.controller.HelloWorldController;
import com.revature.controller.Controller;
import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
        //        ClientDao clientDao = new ClientDao();
        //this willl give us the Javalin object
        //the javalin object is the server
        Javalin app = Javalin.create();
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
