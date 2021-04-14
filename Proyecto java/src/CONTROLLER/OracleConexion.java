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

    public static OracleConexion getInstance(){
        if(conexion == null){
            conexion = new OracleConexion();
        }
        return conexion;
    }

    public void  crearConexion() {
        try {

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                this.connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ADMIN", "ADMIN");
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
        con.modificarTelefono("Batman", "otro", 22562222);
//        try {
//
//            try {
//                Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","ADMIN","ADMIN");
//                System.out.println("conexion exitosa");
//                CallableStatement statement = conexion.prepareCall("CALL MOSTSUB(?)");
//                statement.registerOutParameter(1, OracleTypes.CURSOR);
//                statement.execute();
//                ResultSet resultado = ((OracleCallableStatement)statement).getCursor(1);
//                while(resultado.next()) {
//                    System.out.println(resultado.getString(1));
//                    System.out.println(resultado.getDate(2));
//                    System.out.println(resultado.getFloat(3));
//                }
//                conexion.close();
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                System.out.println("conexion fallida");
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("error de  conexion");
//        }

    }

}