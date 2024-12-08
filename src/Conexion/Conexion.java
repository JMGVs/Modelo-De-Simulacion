package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String USUARIO = "root";
    private static String PASSWORD = "Ajedrez2004";
    private static String URL = "jdbc:mysql://localhost:3309/simulation";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN EL DRIVER: " + e);
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            //JOptionPane.showMessageDialog(null, "CONEXION EXITOSA");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXION" + e);
        }
        return con;
    }
}


 