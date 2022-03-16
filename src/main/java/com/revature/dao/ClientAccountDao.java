package com.revature.dao;

import com.revature.model.ClientAccount;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientAccountDao {

    //get all accounts by id
   // GET /clients/{client_id}/accounts: Get all accounts for client with id of X (if client exists)
    public ClientAccount getAccountByClientId(int clientId) throws SQLException {

        try (Connection con = ConnectionUtility.getConnection()) {
            String query = "SELECT clients.first_name,clients.last_name,clients.age, accounts.account_id," +
                    "accounts.account_type,accounts.account_number,accounts.client_id ,accounts.balance" +
                    " FROM clients left join accounts on clients.id = accounts.client_id where clients.id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int acClientId = rs.getInt("client_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");
                int acId = rs.getInt("account_id");
                String acType = rs.getString("account_type");
                int acNumber = rs.getInt("account_number");
                int acBalance = rs.getInt("balance");

                return new ClientAccount(acClientId, firstName, lastName, age, acId, acType, acNumber, acBalance);
            }

        }
        return null;
    }
    //GET /clients/{client_id}/accounts: Get all accounts for client with id of X (if client exists)
//get all clients

    public List<ClientAccount> getAllAccountsByClientId(int id1) throws SQLException {
        List<ClientAccount> clientAccounts = new ArrayList<>();
        try (Connection con = ConnectionUtility.getConnection()) {
//            String query = "SELECT * FROM clients , accounts where clients.id = accounts.client_id;";
            String query = "SELECT * FROM clients , accounts where clients.id= ?;";


            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");

                int acId = rs.getInt("account_id");
                String acType = rs.getString("account_type");
                int acNumber = rs.getInt("account_number");
                int acClientId = rs.getInt("client_id");
                int acBalance = rs.getInt("balance");

                ClientAccount clientAccount = new ClientAccount(id, firstName, lastName, age, acId, acType, acNumber, acClientId, acBalance);
                clientAccounts.add(clientAccount);
            }

        }
        return clientAccounts;
    }

    //Get account with id of Y belonging to client with id of X (if client and account exist AND if account belongs to client)
    // GET /clients/{client_id}/accounts/{account_id}
    public ClientAccount getAccountByClientIdAccountId(int clientId,int accountId) throws SQLException {

        try (Connection con = ConnectionUtility.getConnection()) {
            String query = "SELECT clients.first_name,clients.last_name,clients.age,"+
                    "accounts.account_type,accounts.account_number ,accounts.balance,accounts.client_id,accounts.account_id" +
                    " FROM clients left join accounts on clients.id = accounts.client_id where clients.id = ? AND accounts.account_id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, clientId);
            pstmt.setInt(2, accountId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int acClientId = rs.getInt("client_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");
                int acId = rs.getInt("account_id");
                String acType = rs.getString("account_type");
                int acNumber = rs.getInt("account_number");
                int acBalance = rs.getInt("balance");

                return new ClientAccount(acClientId, firstName, lastName, age, acId, acType, acNumber, acBalance);
            }

        }
        return null;
    }
    //GET /clients/{client_id}/accounts?amountLessThan=2000&amountGreaterThan=400: Get all accounts for client id of X
    //with balances between 400 and 2000 (if client exists)
    public List<ClientAccount> getAccountsByClientIdBalance(int id1, int bl, int bh) throws SQLException {
        List<ClientAccount> clientAccounts = new ArrayList<>();
        try (Connection con = ConnectionUtility.getConnection()) {
            String query = "SELECT * FROM clients , accounts where clients.id= ? AND accounts.balance<? AND " +
                    "accounts.balance>?;";


            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id1);
            pstmt.setInt(2, bl);
            pstmt.setInt(3, bh);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");

                int acId = rs.getInt("account_id");
                String acType = rs.getString("account_type");
                int acNumber = rs.getInt("account_number");
                int acClientId = rs.getInt("client_id");
                int acBalance = rs.getInt("balance");

                ClientAccount clientAccount = new ClientAccount(id, firstName, lastName, age, acId, acType, acNumber, acClientId, acBalance);
                clientAccounts.add(clientAccount);
            }

        }
        return clientAccounts;
    }



    //Create new account with the help of client id
    //POST /clients/{client_id}/accounts: Create a new account for a client with id of X (if client exists)
    public ClientAccount addClientAccount(ClientAccount clientAccount) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "INSERT INTO accounts (account_type, account_number,client_id,balance) VALUES (?, ?, ?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, clientAccount.getAcType());
            pstmt.setInt(2, clientAccount.getAcNumber());
            pstmt.setInt(3, clientAccount.getAcClientId());
            pstmt.setInt(4, clientAccount.getAcBalance());

             pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt(1);
            return new ClientAccount(generatedId, clientAccount.getAcType(), clientAccount.getAcNumber(),
            clientAccount.getAcClientId(), clientAccount.getAcBalance());
        }
//        return clientAccount;
    }

    //U
    //PUT /clients/{client_id}/accounts/{account_id}: Update account with id of Y belonging to client with id
  //  of X (if client and account exist AND if account belongs to client)
    public ClientAccount updateClientAccount(ClientAccount clientAccount) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "UPDATE accounts SET account_type= ?, account_number = ?, client_id = ?, balance = ? WHERE account_id =?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, clientAccount.getAcType());
            pstmt.setInt(2, clientAccount.getAcNumber());
            pstmt.setInt(3, clientAccount.getAcClientId());

            pstmt.setInt(4, clientAccount.getAcBalance());
            pstmt.setInt(5, clientAccount.getAcId());
            pstmt.executeUpdate();

        }
        return clientAccount;
    }
        // DELETE /clients/{client_id}/accounts/{account_id}: Delete account with id of Y belonging to client with id of
        // X (if client and account exist AND if account belongs to client)
        //Delete account with the client and account id
        //true if a record was deleted, false if a record was not deleted
    public boolean deleteAccountOfClient(int account_id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "DELETE FROM accounts WHERE accounts.account_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

//            pstmt.setInt(1, id);
            pstmt.setInt(1, account_id);

            int numberOfRecordsDeleted = pstmt.executeUpdate(); //executeUpdate() is used with Insert, Update, Delete

            if (numberOfRecordsDeleted == 1) {
                return true;
            }
        }
        return false;
    };


}

