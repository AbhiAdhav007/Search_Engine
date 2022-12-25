package com.Ab;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    static Connection connection = null;

    public static Connection getConnection(){

        if(connection != null){
            return connection;
        }
        String db = "searchengine";
        String user = "root";
        String pass = "Abhi@7449";
        return getConnection(db ,user ,pass);

    }
    private static Connection getConnection(String db , String user , String pass){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Setting the connection to the SQL server
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pass);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return connection;
    }
}