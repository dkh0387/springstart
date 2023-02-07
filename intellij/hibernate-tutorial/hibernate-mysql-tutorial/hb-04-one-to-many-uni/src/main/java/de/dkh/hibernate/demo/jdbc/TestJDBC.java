package de.dkh.hibernate.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://localhost:3307/hb-04-one-to-many-uni?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String password = "hbstudent";

        try {
            System.out.printf("Connecting to database: %s%n", jdbcURL);

            Connection connection = DriverManager.getConnection(jdbcURL, user, password);

            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}