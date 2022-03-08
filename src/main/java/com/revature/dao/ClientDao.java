    package com.revature.dao;

    import com.revature.model.Client;
    import com.revature.utility.ConnectionUtility;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    //TODO 6: Create a Dao (data access object) for a particular 'entity'
    public class ClientDao {

    //TODO 8 : Create the method for the CRUD operations
    //return type of Client because we want to actually return back to whoever is trying to add th client record the record
    // that they add themselves
    //C

        //whenever you add a client, no id is associated yet
        //so what we want to do is retrieve an id and return that with the client object
        //we are taking in a client object and returning another client object with the id populated as id is automatically
        //generated
    public Client addClient(Client client) throws SQLException {
               try (Connection con = ConnectionUtility.getConnection()){
                   String sql = "INSERT INTO clients (first_name, last_name, age) VALUES (?,?,?)";
//whenever we execute the Statement.RETURN_GENERATED_KEYS, the sql will generate the key as well
                   PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                   pstmt.setString(1, client.getFirstName());
                   pstmt.setString(2, client.getLastName());
                   pstmt.setInt(3, client.getAge());

                  pstmt.executeUpdate();

                  ResultSet rs = pstmt.getGeneratedKeys();
                  rs.next();
                  int generateId = rs.getInt(1); //1st column of the result set

                   return new Client(generateId, client.getFirstName(), client.getLastName(), client.getAge());
               }

    }

    //R
    public Client getClientById(int id) throws SQLException {
//        return null;
        //TODO 9 : Call the getConnection method from ConnectionUtility (which we made)
        //we need to have some kind of block that we to automatically close whenever the block is done
        try (Connection con = ConnectionUtility.getConnection()) {// try with resources
            //TODO 10: Create a (Prepared)Statement object using Connection object
            String sql = "SELECT * FROM clients where id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            //TODO 11: If any parameters need to be set, set the parameter (?)
            pstmt.setInt(1,id);

            //TODO 12: Execute the query and retrieve a ResultSet object
            ResultSet rs = pstmt.executeQuery();

            //TODO 13: Iterate over record(s) using the ResulltSet's next() method
            //next method iterate to the next record, it also returns the boolean, true if there is a record,
            //false if there is not  a record
            if (rs.next()) {
                //TODO 14: Gab the information from the record
//                int clientId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_Name");
                int age = rs.getInt("age");

                return new Client(id, firstName, lastName, age);
            }
        }
        return null;
    }

    public List<Client> getAllClients() throws SQLException {
//Declare a array list of clients, this going to be a array list that we return back
        List<Client> clients = new ArrayList<>();
        //we need to have some kind of block that we to automatically close whenever the block is done
        try (Connection con = ConnectionUtility.getConnection()) {// try with resources
            String sql = "SELECT * FROM clients";
            PreparedStatement pstmt = con.prepareStatement(sql);
//here pstmt is executing query, it is used with select
            ResultSet rs = pstmt.executeQuery();
            //looping through all the records
            while (rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");

                //adding client object to the client list
                clients.add(new Client(id, firstName, lastName, age));
            }
        }
        return clients;
    }

    //U
        public Client updateClient(Client client) throws SQLException {
          try(Connection con = ConnectionUtility.getConnection()){
              String sql = "UPDATE clients " +
                      "SET first_name = ?," +
                      "last_name = ?," +
                      "age = ? " +
                      "WHERE id = ?";

              PreparedStatement pstmt = con.prepareStatement(sql);

              pstmt.setString(1, client.getFirstName());
              pstmt.setString(2,client.getLastName());
              pstmt.setInt(3,client.getAge());
              pstmt.setInt(4, client.getId());

              pstmt.executeUpdate();
          }
          return client;
}

//D
        //true if a record was deleted, false if a record was not deleted
    public boolean deleteClientById(int id) throws SQLException {
    try(Connection con = ConnectionUtility.getConnection()){
        String sql = "DELETE FROM clients WHERE id= ?";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setInt(1,id);

        int numberOfRecordsDeleted = pstmt.executeUpdate(); //executeUpdate() is used with Insert, Update, Delete

        if(numberOfRecordsDeleted ==1){
          return true;
        }
    }
    return false;
    }
    }