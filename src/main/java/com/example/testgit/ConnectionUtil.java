package com.example.testgit;

import org.yaml.snakeyaml.events.Event;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;


public class ConnectionUtil {

    public Connection connect_to_db() {

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/LearnGit", "postgres", "romnetanel11");
            System.out.println("Connection Established");

        } catch (Exception e) {
            System.out.println("failed");
            System.out.println(e);
        }
        return conn;
    }
    public void db_data_to_list(ArrayList<users> data) throws SQLException{
        Statement statement;
        Connection connection=connect_to_db();
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM users ORDER BY id";
            ResultSet res = statement.executeQuery(query);
            int i = 0;
            while(res.next()){
                String user_name = res.getString("user_name");
                String phone_number = res.getString("phone_number");
                String email = res.getString("Email");
                String last_name = res.getString("last_name");
                int ID = res.getInt("id");
                //System.out.println(user_name + ", " + ID);
                data.add(new users(ID, user_name, phone_number, email, last_name));
                if (!(in_array_list(ID, data))){
                    data.add(new users(ID, user_name, phone_number, email, last_name));
                }
                if ((i<data.size())&&((data.get(i).getID())==0)){
                    data.get(i).setID(ID);;
                }
                if ((i<data.size())&&data.get(i).getUser_name()!=user_name){
                    data.get(i).setUser_name(user_name);
                }
                i++;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i).getID())==0){
                insert((data.get(i).getUser_name()),data);
            }
        }

    }

    /*
    public void createTable(Connection conn, String recovery) {

        Statement statement;
        try {
            String query = "create table " + recovery + "(id SERIAL PRIMARY KEY NOT NULL, balance numeric(10, 2) default 0, social_security int4 NOT NULL UNIQUE);";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("please enter your social security number");

        } catch (Exception e) {
            System.out.println("entering db information");
            System.out.println("please enter your social security number **1 to exit program**");
        }
    }
    public void insert_row(Connection conn, int socials, double transfer) {
        Statement statement;
        try {
            String query = "INSERT INTO recovery(balance, social_security) VALUES (" + transfer + "," + socials + ");";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("row inserted");

        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

/*    public void balance(String recovery) throws SQLException {
        Statement statement;
        try {
            String query = "SELECT * FROM " + recovery + ";";
            Connection conn=connect_to_db();
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String privatebalances = result.getString("balance");
                String socialsecurity = result.getString("social_security");

                System.out.printf("%d -bal %s -ss %s\n", id, privatebalances, socialsecurity);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    */
    /*    public void delete(Connection conn, int idm) {
        Statement statement;
        try {
            String query = "DELETE FROM RECOVERY WHERE id="+idm+";";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("account with id " +idm+" deleted" + "\n");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    */
    public void insert(String userName, ArrayList<users> data){
        Statement statement;
        try {
            String query = String.format("INSERT INTO users(\n" +
                    "\t\"user_name\")\n" +
                    "\tVALUES ('%s');", userName);
            Connection conn=connect_to_db();
            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("rom");
            //db_data_to_list(data);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void select(Connection conn, ArrayList<users> data) throws SQLException{
        Statement statement;
        try {
            statement = conn.createStatement();
            String query = "SELECT * FROM users ORDER BY id";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                String user_name = res.getString("user_name");
                String phone_number = res.getString("phone_number");
                String email = res.getString("Email");
                String last_name = res.getString("last_name");
                int ID = res.getInt("id");
                System.out.println(user_name + ", " + ID);
                //data.add(new users(ID, user_name, phone_number, email, last_name));
                db_data_to_list(data);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }
    public boolean in_array_list(int ID, ArrayList<users> data){
        for (int i = 0; i < data.size(); i++) {
            if (ID == data.get(i).getID()){
                return true;
            }
        }
        return false;
    }

    public void update(Connection conn, String Name, int id, ArrayList<users> data){
        try {
            //String query = String.format("UPDATE com.example.testgit.users SET user_name = '%s'
            // WHERE id = %s", Name, Integer.toString(id));
            String sqlUpdate = "UPDATE users\n" +
                    "\tSET  user_name = ?" +
                    "\tWHERE id = ?;";
            PreparedStatement statement = conn.prepareStatement(sqlUpdate);
            statement.setString(1,Name);
            statement.setInt(2,id);
            statement.executeUpdate();
            //statement = conn.createStatement();
            System.out.println("Work!");
            //db_data_to_list(conn, data);
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }
}