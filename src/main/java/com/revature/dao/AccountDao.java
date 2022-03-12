package com.revature.dao;

import com.revature.model.Account;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        try (Connection con = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM accounts";
            PreparedStatement pstmt  = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                int acId = rs.getInt("account_id");
                String acType = rs.getString("account_type");
                String acNumber = rs.getString("account_number");
                int acClientId = rs.getInt("client_id");
                int acBalance = rs.getInt("balance");

                Account account = new Account(acId, acType, acNumber, acClientId,acBalance);
                accounts.add(account);
            }
        }
        return accounts;
    }
}
