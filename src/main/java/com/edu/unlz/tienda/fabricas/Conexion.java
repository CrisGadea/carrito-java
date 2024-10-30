package com.edu.unlz.tienda.fabricas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static  final String SERVER = "jdbc:mysql://localhost:3306";
    private static  final String BASE = "tp_java2024";
    private static  final String USER = "root";
    private static  final String PASSWORD = "12345678";

    public static Connection getConexion() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String conectionString = SERVER+"/"+BASE;
        return DriverManager.getConnection(conectionString,USER,PASSWORD);
    }
}
