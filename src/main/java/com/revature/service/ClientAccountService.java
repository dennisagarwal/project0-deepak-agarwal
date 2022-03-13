package com.revature.service;

import com.revature.dao.ClientAccountDao;
import com.revature.dao.ClientDao;
import com.revature.exception.ClientAccountNotFoundException;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import com.revature.model.ClientAccount;

import java.sql.SQLException;
import java.util.List;

public class ClientAccountService {



    private ClientAccountDao clientAccountDao;


    public ClientAccountService() {

        this.clientAccountDao = new ClientAccountDao();

    }

    public ClientAccount getAccountByClientId(String id) throws SQLException, ClientAccountNotFoundException {
        int clientIdInParam = Integer.parseInt(id);
        ClientAccount ca = clientAccountDao.getAccountByClientId(clientIdInParam);

        if (ca == null) {
            throw new ClientAccountNotFoundException("Client with id " + clientIdInParam + " was not found");
        }
        return ca;
    }

    public ClientAccount addClientAccount(ClientAccount ca) throws SQLException {

        if((!ca.getAcType().matches("[a-zA-Z]+")) | ca.getAcNumber()==0 | ca.getAcClientId()==0 | ca.getAcBalance()<0){
            throw new IllegalArgumentException("Some Invalid data entered account type " + ca.getAcType() +" Account Balance "+
                    ca.getAcBalance() + " ClientId " + ca.getAcClientId()+ " Account Number " +  ca.getAcNumber())
                    ;}

        ClientAccount addedClientAccount = clientAccountDao.addClientAccount(ca);
        return addedClientAccount;
    }

    public ClientAccount editClientAccount(String id , ClientAccount ca) throws SQLException, ClientAccountNotFoundException{
        try{
            int clientIdInParam = Integer.parseInt(id);
            if(clientAccountDao.getAccountByClientId(clientIdInParam)==null | clientAccountDao.getAllClientAccounts()==null ){
                throw new ClientAccountNotFoundException("user is trying to edit client account which does not exist " +
                        ca.getAcClientId() + " not found.");
            }
            ca.setId(clientIdInParam);
            ClientAccount editedClientAccount = clientAccountDao.updateClientAccount(ca);
            return editedClientAccount;
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("Client provide must be a valid int");
        }
    }

    public boolean deleteAccountOfClient(String id) throws SQLException, ClientNotFoundException {
        try {
            int accountIdInParam = Integer.parseInt(id); //this could throw an unchecked exception
            //known as NumberFormatException
            //important to note because any unhandled exceptions will result in a 500 Internal Server Error
            //(which we should try to avoid)

            boolean ca = clientAccountDao.deleteAccountOfClient(accountIdInParam); //this could return null
            if (ca == false) {
                throw new ClientNotFoundException("Account with id " + accountIdInParam + " was not found");
            }
            return true;

        } catch (NumberFormatException e) {
            //illegal argument exception is a part of Java library
            throw new IllegalArgumentException("Id provided for account must be a valid int");
        }

    }

//    public List<ClientAccount> getAllClientAccounts() throws SQLException {
//        return this.clientAccountDao.getAllClientAccounts();
//    }

    public ClientAccount getAccountByClientIdAccountId(String id1, String id2) throws SQLException, ClientAccountNotFoundException {
        int clientId = Integer.parseInt(id1);
        int accountId = Integer.parseInt(id2);

       ClientAccount ca = clientAccountDao.getAccountByClientIdAccountId(clientId,accountId);
        if(ca == null){
            throw new ClientAccountNotFoundException("Client Id " + clientId + " or Account Id " + accountId + " not found ");
        }
       return ca;
    }
}
