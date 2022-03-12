package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private static Logger logger = LoggerFactory.getLogger(ClientService.class);

    private ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
    }

    public ClientService(ClientDao mockDao){
        this.clientDao = mockDao;
    }

    public List<Client> getAllClients() throws SQLException {
        return this.clientDao.getAllClients();
    }

    public Client getClientById(String id) throws SQLException, ClientNotFoundException {
        try {
            int clientId = Integer.parseInt(id); //this could throw an unchecked exception
            //known as NumberFormatException
            //important to note because any unhandled exceptions will result in a 500 Internal Server Error
            //(which we should try to avoid)

            Client c = clientDao.getClientById(clientId); //this could return null

            if (c == null) {
                throw new ClientNotFoundException("Client with id " + clientId + " was not found");
            }
            return c;
        } catch (NumberFormatException e) {
            //illegal argument exception is a part of Java library
            throw new IllegalArgumentException("Id provided for client must be a valid int");
        }

    }

    public Client addClient(Client c) throws SQLException {

        validateClientInformation(c);
        Client addedClient = clientDao.addClient(c);
        return addedClient;
    }

    //If we are editing a client, we must check if the client exist
    public Client editClient(String id, Client c) throws SQLException, ClientNotFoundException {
        try {
            int clientId = Integer.parseInt(id);

            if(clientDao.getClientById(clientId) == null) {
                throw new ClientNotFoundException("User is trying to edit a Client that does not exist. CLient with id" + clientId + "was not found");
            }
            validateClientInformation(c);
            c.setId(clientId);

            Client editedClient = clientDao.updateClient(c);
            return editedClient;

        } catch(NumberFormatException e){
            throw new IllegalArgumentException("Id provide for client must be a valid int");
        }
    }
    public void validateClientInformation(Client c){
        c.setFirstName(c.getFirstName().trim());
        c.setLastName(c.getLastName().trim());

        if(!c.getFirstName().matches("[a-zA-Z]+")){
            throw new IllegalArgumentException("First name must only have alphabetical characters" + c.getFirstName());
        }

        if(!c.getLastName().matches("[a-zA-Z]+")){
            throw new IllegalArgumentException("Last name must only have alphabetical characters" + c.getLastName());
        }

        if(c.getAge()<0){
            throw new IllegalArgumentException("Adding a client with age < 0 is not valid. Age provided was "+ c.getAge());
        }
    }
}

