package CONTROLLER;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerAdmin {

    public ControllerAdmin() {}

    public int registrarUsusuario(boolean esAdmin, String alias, String contrasena, String cedula, String nombre, String apellidos, String direccion){
        int codigoExito = 0;

        try {
            String llamadaFuncion = "SELECT * FROM registrarusuario(?,?,?,?,?,?,?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setBoolean(1, esAdmin);
            statement.setString(2, alias);
            statement.setString(3, contrasena);
            statement.setString(4, cedula);
            statement.setString(5, nombre);
            statement.setString(6, apellidos);
            statement.setString(7, direccion);

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

    public ArrayList<String> tipos(){
        ArrayList<String> tipos = new ArrayList<>();


        try {
            String llamadaFuncion = "SELECT * FROM mostrartipostel()";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tipos.add(resultSet.getString(1));
            }


        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }
        return tipos;

    }

    public int agregarTelefono(String alias, String contrasenna, int numero, String tipo){
        int codigo = 0;

        try {
            String llamadaFuncion = "SELECT * FROM agregartelefono(?,?,?,?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);
            statement.setString(1, alias);
            statement.setString(2, contrasenna);
            statement.setInt(3, numero);
            statement.setString(4, tipo);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                codigo = resultSet.getInt(1);
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
            codigo = 0;

        }
        return codigo;

    }

    public int mostrarSubastasActivas(){
        int codigo = 0;

        try {
            String llamadaFuncion = "SELECT * FROM mostrarsubastasactivas()";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                codigo = resultSet.getInt(1);
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
            codigo = 0;

        }
        return codigo;

    }

//    public static void main(String[] args){
//        ControllerAdmin controllerAdmin = new ControllerAdmin();
//        int codigo = controllerAdmin.agregarTelefono("Peter", "gatoFeliz12", 4564979, "CELULAR");
//        System.out.println(codigo);
//    }
}
