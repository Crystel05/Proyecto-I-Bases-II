package CONTROLLER;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerInicioSesionPostgre {

    public ControllerInicioSesionPostgre() {}

    public int iniciarSesion(boolean esAdmin, String alias, String contrasena){
        int codigoExito = 0;

        try {
            String llamadaFuncion = "SELECT * FROM verificariniciosesion(?,?,?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setBoolean(1, esAdmin);
            statement.setString(2, alias);
            statement.setString(3, contrasena);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                if(resultSet.getInt(1) == 1) {
                    codigoExito = 1;

                }
                else
                    codigoExito = 0;
            }


        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
            codigoExito = 0;
        }

        return codigoExito;
    }



}
