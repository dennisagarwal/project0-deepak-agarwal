package com.revature.service;

import com.revature.dao.ClientAccountDao;
import com.revature.exception.ClientAccountNotFoundException;
import com.revature.model.ClientAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientAccountServiceTest {

    @Test
    public void testgetAccountByClientId() throws ClientAccountNotFoundException, SQLException {
        ClientAccountDao mockDao = mock(ClientAccountDao.class);
        when(mockDao.getAccountByClientId(eq(10))).thenReturn(
                new ClientAccount(0, "Saving", 1010, 10, 90000, 13)
        );
        ClientAccountService clientAccountService = new ClientAccountService(mockDao);
        ClientAccount clientAccount = clientAccountService.getAccountByClientId("10");
        System.out.println(clientAccount);
    }

    //Negetive Test
    @Test
    public void test_getAccountByClientId_DoesNotExist() throws SQLException {
        //Arrange
        ClientAccountDao mockDao = mock(ClientAccountDao.class);
        ClientAccountService clientAccountService = new ClientAccountService(mockDao);

        //Act + Assert
        Assertions.assertThrows(ClientAccountNotFoundException.class, () -> {
            clientAccountService.getAccountByClientId("10");
        });

    }

    //Negetive Test
    @Test
    public void test_getAccountByClientIdAccountId_AccountDoesNotExist() throws SQLException {
        //Arrange
        ClientAccountDao mockDao = mock(ClientAccountDao.class);
        ClientAccountService clientAccountService = new ClientAccountService(mockDao);

        //Act + Assert
        Assertions.assertThrows(ClientAccountNotFoundException.class, () -> {
            clientAccountService.getAccountByClientIdAccountId("10", "32");
        });
    }

    //NegetiveTest
    @Test
    public void test_getAccountByClientIdAccountId_InvalidId() throws SQLException, ClientAccountNotFoundException {
        //Arrange
        ClientAccountDao mockDao = mock(ClientAccountDao.class);
        ClientAccountService clientAccountService = new ClientAccountService(mockDao);
        //Act + Assert
        Assertions.assertThrows(NumberFormatException.class, () -> {
            clientAccountService.getAccountByClientIdAccountId("abc", "abc");
        });
    }

    //Negetive
    @Test
    public void test_getAccountByyClientIdAccountId_sqlException() throws SQLException{
        //Arrange
        ClientAccountDao mockDao = mock(ClientAccountDao.class);
        when(mockDao.getAccountByClientIdAccountId(anyInt(),anyInt())).thenThrow(SQLException.class);
        ClientAccountService clientAccountService = new ClientAccountService(mockDao);

        //Act + Assert
        Assertions.assertThrows(SQLException.class, () -> {
            clientAccountService.getAccountByClientIdAccountId("5","8");

        });
    }
}





