package com.revature.dao;

import com.revature.model.ClientAccount;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientAccountDao {

    public ClientAccount getAccountByClientId(int clientId) throws SQLException {


        try (Connection con = ConnectionUtility.getConnection()) {
            String query = "SELECT clients.first_name,clients.last_name,clients.age, accounts.client_id FROM clients left join accounts on clients.id = accounts.client_id where clients.id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int acClientId = rs.getInt("client_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");

                return new ClientAccount(acClientId, firstName, lastName, age);
            }


        }
        return null;
    }


    public List<ClientAccount> getAllClientAccounts() throws SQLException {
        List<ClientAccount> clientAccounts = new ArrayList<>();
        try (Connection con = ConnectionUtility.getConnection()) {
            String query = "SELECT * FROM clients , accounts where clients.id = accounts.client_id;";

            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");

                int acId = rs.getInt("account_id");
                String acType = rs.getString("account_type");
                String acNumber = rs.getString("account_number");
                int acClientId = rs.getInt("client_id");
                int acBalance = rs.getInt("balance");

                ClientAccount clientAccount = new ClientAccount(id, firstName, lastName, age, acId, acType, acNumber, acClientId, acBalance);
                clientAccounts.add(clientAccount);
            }

        }
        return clientAccounts;
    }

}
