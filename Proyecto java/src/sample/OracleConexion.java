package sample;

import java.sql.*;


public class OracleConexion {

    public static void main(String[] args) {
        try {

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","ADMIN","ADMIN");
                System.out.println("conexion exitosa");
                Statement statement = conexion.createStatement();
                ResultSet resultado = statement.executeQuery("SELECT * FROM tipotelefono");
                while(resultado.next())
                    System.out.println(resultado.getString(2));
                conexion.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("conexion fallida");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("error de  conexion");
        }

    }

}