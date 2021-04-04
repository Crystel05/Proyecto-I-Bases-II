package sample;

import oracle.jdbc.*;
import java.sql.*;


public class OracleConexion {

    public static void main(String[] args) {
        try {

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","ADMIN","ADMIN");
                System.out.println("conexion exitosa");
                CallableStatement statement = conexion.prepareCall("CALL VERIFICARADMIN(?,?)");
                statement.setString(1,"Batman");
                statement.registerOutParameter(2, OracleTypes.CURSOR);

                statement.execute();
                ResultSet resultado = ((OracleCallableStatement)statement).getCursor(2);
                while(resultado.next()) {
                    System.out.println(resultado.getString(1));
                    //System.out.println(resultado.getInt(3));
                }
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