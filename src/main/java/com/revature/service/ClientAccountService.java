package com.revature.service;

import com.revature.dao.ClientAccountDao;
import com.revature.exception.ClientAccountNotFoundException;
import com.revature.model.ClientAccount;

import java.sql.SQLException;

public class ClientAccountService {

    private ClientAccountDao clientAccountDao;

    public ClientAccountService(){

        this.clientAccountDao = new ClientAccountDao();
        }
    public ClientAccount getAccountByClientId(String id) throws SQLException, ClientAccountNotFoundException {
        int clientIdInParam = Integer.parseInt(id);
        ClientAccount ca = clientAccountDao.getAccountByClientId(clientIdInParam);

        if(ca== null){
            throw new ClientAccountNotFoundException("Client with id " +  clientIdInParam + " was not found");
        }
        return ca;
    }

}
