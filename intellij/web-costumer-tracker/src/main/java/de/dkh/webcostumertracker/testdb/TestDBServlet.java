package de.dkh.webcostumertracker.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Servlet implementation for testing the MySQL connection.
 * In order to run this we just need to start the defined tomcat Run configuration with the webapp
 * and navigate to `http://localhost:8080/TestDBServlet`.
 */
@WebServlet(name = "TestDBServlet", value = "/TestDBServlet")
public class TestDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = "springstudent";
        String password = "springstudent";
        String jdbcURL = "jdbc:mysql://localhost:3307/web_customer_tracker?useSSL=false";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database :" + jdbcURL + " ...");

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(jdbcURL, user, password);
            out.println("Connection successful!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Connection failed!");
        }

    }

}
