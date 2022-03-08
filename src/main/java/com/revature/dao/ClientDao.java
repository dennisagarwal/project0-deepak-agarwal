    package com.revature.dao;

    import com.revature.model.Client;
    import com.revature.utility.ConnectionUtility;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    //TODO 6: Create a Dao (data access object) for a particular 'entity'
    public class ClientDao {

    //TODO 8 : Create the method for the CRUD operations
    //return type of Client because we want to actually return back to whoever is trying to add th client record the record
    // that they add themselves
    //C
    public Client addClients(Client client)  {
               return null;

    }

    //R
    public Client getClientsById(int id) throws SQLException {
//        return null;
        //TODO 9 : Call the getConnection method from ConnectionUtility (which we made)
        //we need to have some kind of block that we to automatically close whenever the block is done
        try (Connection con = ConnectionUtility.getConnection()) {// try with resources
            //TODO 10: Create a (Prepared)Statement object using Connection object
            String sql = "SELECT * FROM students where id = ?";
            PreparedStatement pstm = con.prepareStatement(sql);

            //TODO 11: If any parameters need to be set, set the parameter (?)
            pstm.setInt(1,id);

            //TODO 12: Execute the query and retrieve a ResultSet object
            ResultSet rs = pstm.executeQuery();

            //TODO 13: Iterate over record(s) using the ResulltSet's next() method
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
//Declare a array list of students, this going to be a array list that we return back
        List<Client> clients = new ArrayList<>();
        //we need to have some kind of block that we to automatically close whenever the block is done
        try (Connection con = ConnectionUtility.getConnection()) {// try with resources
            String sql = "SELECT * FROM students";
            PreparedStatement pstm = con.prepareStatement(sql);
//here pstm is executng query, it is used with select
            ResultSet rs = pstm.executeQuery();

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
        public Client updateClient(Client client){
           return null;
}

//D
        //true if a record was deleted, false if a record was not deleted
    public boolean deleteClientById(int id) throws SQLException {
    try(Connection con = ConnectionUtility.getConnection()){
        String sql = "DELETE FROM students WHERE id= ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        int numberOfRecordsDeleted = pstm.executeUpdate(); //executeUpdate() is used with Insert, Update, Delete

        if(numberOfRecordsDeleted ==1){
          return true;
        }
    }
    return false;
    }
    }