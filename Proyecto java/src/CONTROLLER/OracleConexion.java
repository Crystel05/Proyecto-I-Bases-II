package CONTROLLER;

import oracle.jdbc.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OracleConexion {

    private static OracleConexion conexion;
    public Connection connection;

    private OracleConexion() {
        crearConexion();
    }

    public static OracleConexion getInstance() {
        if (conexion == null) {
            conexion = new OracleConexion();
        }
        return conexion;
    }

    public void crearConexion() {
        try {

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                this.connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "Crystel", "gatoscools05");
                System.out.println("conexion exitosa");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("conexion fallida");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("error de  conexion");
        }
    }

    public static void main(String[] args) {

        ControllerSubasta con = new ControllerSubasta();
        System.out.println(con.nombreVendedor("xlz5050"));
        //con.pujar("Deku","12345",15000, "kit de risas");
        //con.getSubCategorias("Juguetes y pasatiempos");
    }
}