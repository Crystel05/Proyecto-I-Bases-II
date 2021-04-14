package CONTROLLER;

import MODEL.*;

import java.math.BigDecimal;
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

    public ArrayList<Subasta> mostrarSubastasActivas(){
        ArrayList<Subasta> subastasActivas = new ArrayList<>();

        try {
            String llamadaFuncion = "SELECT * FROM mostrarsubastasactivas()";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subasta subasta = new Subasta();
                Item item = new Item();
                item.setNombre(resultSet.getString(1));
                item.setPathFoto(resultSet.getString(4));
                item.setDetalles(resultSet.getString(5));
                subasta.setItem(item);
                subasta.setFachaFinal(resultSet.getTimestamp(2));
                subasta.setMejorMonto(resultSet.getFloat(3));
                subastasActivas.add(subasta);
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }
        return subastasActivas;

    }

    public int pujar(String alias, String pasw, float monto, String itemNombre){
        int codigo = 0;

        try {
            String llamadaFuncion = "SELECT * FROM pujar(?, ?, ?, ?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, alias);
            statement.setString(2, pasw);
            statement.setBigDecimal(3, BigDecimal.valueOf(monto));
            statement.setString(4, itemNombre);

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

    public ArrayList<String> nombreSubastas(){
        ArrayList<String> nombres = new ArrayList<>();

        try {
            String llamadaFuncion = "SELECT * FROM mostrartodassubastas()";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nombres.add(resultSet.getString(1));
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }
        return nombres;
    }

    public ArrayList<Puja> pujasXsubasta(String nombreItem){
        ArrayList<Puja> pujas = new ArrayList<>();

        try {
            String llamadaFuncion = "SELECT * FROM pujasxsubasta(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, nombreItem);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Puja puja = new Puja(resultSet.getBigDecimal(1), resultSet.getTimestamp(2));
                pujas.add(puja);
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }
        return pujas;
    }

    public ArrayList<Usuario> mostrarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            String llamadaFuncion = "SELECT * FROM mostrarusuarios()";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario(resultSet.getString(1), resultSet.getString(2));
                usuarios.add(usuario);
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }
        return usuarios;
    }

    public ArrayList<tablaComprasXcomprador> comprasXcomprador(String ident){
        ArrayList<tablaComprasXcomprador> compras = new ArrayList<>();

        try {
            String llamadaFuncion = "SELECT * FROM comprasxcomprador(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, ident);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tablaComprasXcomprador item = new tablaComprasXcomprador(resultSet.getString(1),
                        resultSet.getBigDecimal(2), resultSet.getBigDecimal(3), resultSet.getTimestamp(4));
                compras.add(item);
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }

        return compras;
    }

//    public static void main(String[] args){
//        ControllerAdmin controllerAdmin = new ControllerAdmin();
//        ArrayList<String> ced = controllerAdmin.mostrarUsuarios();
//        System.out.println(ced);
//    }
}
