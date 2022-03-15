package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//importing stating mockito method from mockito class
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @Test
    public void testGetAllClients() throws SQLException {
        //Arrange
        ClientDao mockedObject = mock(ClientDao.class);

        List<Client> fakeClients = new ArrayList<>();
        fakeClients.add(new Client(1, "Bill", "Doe", 28));
        fakeClients.add(new Client(2, "John", "Moe", 32));
        fakeClients.add(new Client(3, "Carol", "Smith", 26));

        //whenever the code in the service layer calls the getAllClients()
        //method for the dao layer, then return the list of clients
        //we have specified above

        when(mockedObject.getAllClients()).thenReturn(fakeClients);
        //creating client dao object
        ClientService clientService = new ClientService(mockedObject);
        //Act
        List<Client> actual = clientService.getAllClients();
        //by default return from a mock object is null
        System.out.println(actual);

        //Assert
        List<Client> expected = new ArrayList<>(fakeClients);
        Assertions.assertEquals(expected, actual);

    }

    //Positive test is also known as happy path, the user is utilizing this
    //method correctly
    @Test
    public void testGetStudentById_positiveTest() throws SQLException, ClientNotFoundException {
        // Arrange
        ClientDao mockDao = mock(ClientDao.class);

        // Mocking the return value for id 10
        when(mockDao.getClientById(eq(10))).thenReturn(
                new Client(10, "Test", "Testy", 20)
        );

        ClientService studentService = new ClientService(mockDao);

        // Act
        Client actual = studentService.getClientById("10");

        // Assert
        Client expected = new Client(10, "Test", "Testy", 20);
        Assertions.assertEquals(expected, actual);
    }

    //Negative Test
    @Test
    public void test_getClientById_clientDoesNotExist() throws SQLException {
        //Arrange
        ClientDao mockDao = mock(ClientDao.class);
        ClientService clientService = new ClientService(mockDao);

        //Act + Assert

        //the test case will pass if we encounter this exception
        //(ClientNotFoundException)
        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            clientService.getClientById("10");
        });
    }

    //Negetive Test
    @Test
    public void test_getStudentById_invalidId() throws ClientNotFoundException, SQLException {
        //Arrange
        ClientDao mockDao = mock(ClientDao.class);
        ClientService clientService = new ClientService(mockDao);

        //Act + assert
        try {
            clientService.getClientById("abc");
            fail(); //we only reach this line if no exception is caught
        } catch (IllegalArgumentException e) {
            String expectedMessage = ("Id provided for client must be a valid int");
            String actualMessage = e.getMessage();
            Assertions.assertEquals(expectedMessage, actualMessage);
        }
    }
    //Negative
    @Test
    public void test_getStudentById_sqlExceptionFromDao() throws SQLException {
        //Arrange
        ClientDao mockDao = mock(ClientDao.class);
        when(mockDao.getClientById(anyInt())).thenThrow(SQLException.class);
        ClientService clientService = new ClientService(mockDao);

        //Act + Assert
        Assertions.assertThrows(SQLException.class, ()->{
           clientService.getClientById("5");
        });
    }


}