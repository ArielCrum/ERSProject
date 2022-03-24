package Repositories;

import Models.User;

import java.sql.*;

/*
* What is needed to connect to our postgresql database on AWS(Amazon Web Services) using JDBC?
*   - url/endpoint: location of our database
*          - syntax: jdbc:postgresql://[AWS_RDS_Endpoint]/[Database_name]
*   - username for our aws database
*   - password for our aws database
*   - driver specific to our Database Management System
*       - add driver dependency to pom.xml
*
*   Interfaces and classes of JDBC:
*   - Connection (interface): is the active connection with the database
*   - DriverManager: how we get our connection; retrieves the connection from our database
*   - Statement: object representation of the sql statement (does not prevent SQL injection)
*       - PreparedStatement: object representation of the sql statement (prevents SQL injection)
*   - ResultSet: object representation of the result set
*
* The most common error in JDBC is 'no suitable driver found'
*   - either you don't have the driver
*   - or the URL string is incorrect
*
* */

public class UserDAOImpl implements UserDAO{
    String url = "jdbc:postgresql://ac-fsj-db.cowkwmk3htan.us-east-1.rds.amazonaws.com/Project1";
    String username = "postgres";
    String password = "p4ssw0rd";


    // getting a users information based on the username input
    @Override
    public User getUserGivenUsername(String username) {

        User user = null;

        // by putting our connection in the try resources section, it closes the connection automatically
        try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password);) {
            //retrieve active connection from our database

            String sql = "select * from ers_users where ers_username = ?;" ; //DQL Statements return a result set

            // preparing our sql statement
            PreparedStatement ps = conn.prepareStatement(sql);

            //we are adding the username into the question mark in the sql statement
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            //rs.next: keep looping if theres another record; must be ran at least once before we get our values
            while(rs.next()){ //needs to be ran at least one time before we can get our values for the User Object
                user = new User(rs.getInt(1),    //user Id is colomn 1 so you put 1 in the parenthesis
                        rs.getString(2), //username
                        rs.getString(3), //password
                        rs.getString(4), //firstname
                        rs.getString(5), //lastname
                        rs.getString(6), //email
                        rs.getInt(7));  // user role
            }



        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return user; //if it can't find user it'll return null
    }


    //creating a new user
    @Override
    public void createUser(User user) {

        try(Connection conn= DriverManager.getConnection(this.url, this.username, this.password);){
            // try with resources above: retrieve active connection from our database

            //DML statement does not return a results set
            String sql = "insert into ers_users (ers_username, ers_password, user_firstname, user_lastname, user_email, user_role_id) values (?, ?, ?, ?, ?, ?);";

            // creating a prepared statement
            PreparedStatement ps = conn.prepareStatement(sql);
            // inserting the values for our question marks

                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getEmail());
                ps.setInt(6, user.getRoleID());
                //returns the amount of rows affected

            ps.executeUpdate(); //executes the statement

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}

/*First JDBC statement, Get a User given a Username
*
* The syntax for every DQL statement we do is going to look like lines 37-47*/