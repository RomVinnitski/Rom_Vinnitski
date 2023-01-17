package testgit;

import javax.naming.Name;
import java.sql.*;


public class ConnectionUtil {

    public Connection connect_to_db(String dbname, String user, String pass) {

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
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

    public void balance(Connection conn, String recovery) throws SQLException {
        Statement statement;
        try {
            String query = "SELECT * FROM " + recovery + ";";
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
    public void insert(Connection conn, String userName){
        Statement statement;
        try {
            String query = String.format("INSERT INTO public.users(\n" +
                    "\t\"user_name\")\n" +
                    "\tVALUES ('%s');", userName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void select(Connection conn){
        Statement statement;
        try {
            statement = conn.createStatement();
            String query = "SELECT * FROM users";
            statement.execute(query);
            System.out.println("Work!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }
    public void update(Connection conn, String Name, int id){
        try {
            //String query = String.format("UPDATE users SET user_name = '%s' WHERE id = %s", Name, Integer.toString(id));
            String sqlUpdate = "UPDATE users\n" +
                    "\tSET  user_name = ?" +
                    "\tWHERE id = ?;";
            PreparedStatement statement =conn.prepareStatement(sqlUpdate);
            statement.setString(1,Name);
            statement.setInt(2,id);
            statement.executeUpdate();
            conn.close();
            //statement = conn.createStatement();
            //statement.executeUpdate(sqlUpdate);
            System.out.println("Work!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }


    }
}