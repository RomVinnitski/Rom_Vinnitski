package com.example.testgit;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * the class prints hello world.
 * @author: Rom Netanel Vinnitski, ID: 327549549, 15/01/2023.
 */
@SpringBootApplication
public class TestGitApplication {

    public static void main(String[] args) throws SQLException {
        ConnectionUtil myCon = new ConnectionUtil();
        ArrayList<users> data = new ArrayList<users>();
        myCon.insert("?",data);
        //myCon.select(con, data);
        //Connection con1 = myCon.connect_to_db("LearnGit", "postgres", "romnetanel11");
        //myCon.update(con1, "Ariel", 4, data);

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).getID());
            System.out.println(data.get(i).getUser_name());
            System.out.println(data.get(i).getPhone_number());
            System.out.println(data.get(i).getEmail());
            System.out.println(data.get(i).getLast_name());
        }
    }
}
