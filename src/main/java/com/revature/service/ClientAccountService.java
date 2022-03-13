package com.revature.service;

import com.revature.dao.ClientAccountDao;
import com.revature.dao.ClientDao;
import com.revature.exception.ClientAccountNotFoundException;
import com.revature.model.ClientAccount;

import java.sql.SQLException;

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

}
