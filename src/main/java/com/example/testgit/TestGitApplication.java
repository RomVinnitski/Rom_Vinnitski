package com.example.testgit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testgit.ConnectionUtil;

import java.sql.Connection;

/**
 * the class prints hello world.
 * @author: Rom Netanel Vinnitski, ID: 327549549, 15/01/2023.
 */
@SpringBootApplication
public class TestGitApplication {

    public static void main(String[] args) {
        ConnectionUtil myCon = new ConnectionUtil();
        Connection con = myCon.connect_to_db("LearnGit", "postgres", "romnetanel11");
        myCon.insert(con,"Amit");
    }

}
