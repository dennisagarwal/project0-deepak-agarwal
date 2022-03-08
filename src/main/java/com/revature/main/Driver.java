package com.revature.main;

import com.revature.dao.ClientDao;
import com.revature.model.Client;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao();

        try{
            Client c = new Client(5, "Rad", "Doe", 31);
            System.out.println(clientDao.updateClient(c));
//            System.out.println(clientDao.addClient(c));
//
//            System.out.println(clientDao.getClientById(1));
//
//            clientDao.deleteClientById(3);
//            System.out.println(clientDao.deleteClientById(3));
//            System.out.println(clientDao.getAllClients());
//            System.out.println(clientDao.getAllClients());
        } catch (SQLException e){
            e.printStackTrace();
        }

}
}
