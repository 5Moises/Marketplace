package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    static String URL = "localhost:3306/";
    static String DATABASE_NAME = "Marketplace";
    static String USERNAME = "root";
    static String PASSWORD = "";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + URL + DATABASE_NAME, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return con;
    }
}