package com.espinoza.app.rutas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";
    private static String username = "SYSTEM";
    private static String password = "chuky08";

    //Metodos
    //metodo que establece la conexion al servidor de BD ORACLE
    public static Connection getInstance(){
        try {
            return DriverManager.getConnection(url,username,password);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}