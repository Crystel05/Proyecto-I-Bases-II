package CONTROLLER;

import MODEL.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ControllerAdminPost {

    public ControllerAdminPost() {}

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

    public ArrayList<Usuario> mostrarUsuariosEditar(boolean esAdmin){
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            String llamadaFuncion = "SELECT * FROM mostrarusuarioseditar(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setBoolean(1, esAdmin);

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

    public Usuario mostrarInfo(String ced){
        Usuario usuario = null;

        try {
            String llamadaFuncion = "SELECT * FROM mostrarinfousuario(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, ced);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usuario = new Usuario(resultSet.getString(5), resultSet.getString(1),
                        resultSet.getString(3), resultSet.getString(2), resultSet.getString(4));

            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }

        return usuario;
    }

    public ArrayList<Integer> mostrarTelsU(String ced){
        ArrayList<Integer> numeros = new ArrayList<>();

        try {
            String llamadaFuncion = "SELECT * FROM mostrartelus(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, ced);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numeros.add(resultSet.getInt(1));
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);

        }

        return numeros;
    }

    public Integer modificarTel (int telViejo, int telNuevo, String tipo){
        int codigo = 0;

        try {
            String llamadaFuncion = "SELECT * FROM modificartelefono(?, ?, ?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setInt(1, telViejo);
            statement.setInt(2, telNuevo);
            statement.setString(3, tipo);

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

    public Integer modificarUsuario(String cedulaVieja, String nombre, String aliasNuevo, String contra, String ced, String dir){
        int codigo = 0;

        try {
            String llamadaFuncion = "SELECT * FROM modificarusuario(?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, cedulaVieja);
            statement.setString(2, nombre);
            statement.setString(3, aliasNuevo);
            statement.setString(4, contra);
            statement.setString(5, ced);
            statement.setString(6, dir);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                codigo = resultSet.getInt(1);
            }

        }
        catch (Exception e){
            System.out.println("Error de conexión");
            e.printStackTrace();
            codigo = 0;

        }

        return codigo;
    }


    public ArrayList<String> categorias(){
        ArrayList<String> categorias = new ArrayList<>();

        try{
            String llamadaFuncion = "SELECT * FROM mostcategorias()";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categorias.add(resultSet.getString(1));
            }
        }
        catch (Exception e){
            System.out.println("No se pudo conectar con la base de datos");
            e.printStackTrace();
        }

        return categorias;
    }

    public ArrayList<String> subCategorias(String catNombre){
        ArrayList<String> subCategoria = new ArrayList<>();

        try{
            String llamadaFuncion = "SELECT * FROM mostrarsubcategorias(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, catNombre);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subCategoria.add(resultSet.getString(1));
            }
        }
        catch (Exception e){
            System.out.println("No se pudo conectar con la base de datos");
            e.printStackTrace();
        }

        return subCategoria;
    }

    public ArrayList<Subasta> subastasActivasTotal(){
        ArrayList<Subasta> subastas = new ArrayList<>();

        try{
            String llamadaFuncion = "SELECT * FROM subastasactivastablasinescoger()";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Subasta subasta = new Subasta();
                subasta.setNomIt(resultSet.getString(1));
                subasta.setFachaFinal(resultSet.getTimestamp(2));
                subasta.setMejorMonto(resultSet.getFloat(3));

                subastas.add(subasta);
            }
        }
        catch (Exception e){
            System.out.println("No se pudo conectar con la base de datos");
            e.printStackTrace();
        }

        return subastas;
    }

    public ArrayList<Subasta> subastasActivasCategoria(String categoria){
        ArrayList<Subasta> subastas = new ArrayList<>();

        try{
            String llamadaFuncion = "SELECT * FROM mostsubporcat(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, categoria);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Subasta subasta = new Subasta();
                subasta.setNomIt(resultSet.getString(1));
                subasta.setFachaFinal(resultSet.getTimestamp(2));
                subasta.setMejorMonto(resultSet.getFloat(3));

                subastas.add(subasta);
            }
        }
        catch (Exception e){
            System.out.println("No se pudo conectar con la base de datos");
            e.printStackTrace();
        }

        return subastas;
    }

    public ArrayList<Subasta> subastasActivasFinal(String subCategoria){
        ArrayList<Subasta> subastas = new ArrayList<>();

        try{
            String llamadaFuncion = "SELECT * FROM mostsubporsubcat(?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, subCategoria);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Subasta subasta = new Subasta();
                subasta.setNomIt(resultSet.getString(1));
                subasta.setFachaFinal(resultSet.getTimestamp(2));
                subasta.setMejorMonto(resultSet.getFloat(3));

                subastas.add(subasta);
            }
        }
        catch (Exception e){
            System.out.println("No se pudo conectar con la base de datos");
            e.printStackTrace();
        }

        return subastas;
    }

    public Integer iniciarSubasta(String nombreI, String detallesItem, String pathFoto, String subcat, float montoIni, String fechaFin,
                                  String detalles, String alias, String contrasenna, float montoMin){

        int codigo = 0;

        try{
            String llamadaFuncion = "SELECT * FROM crearSubasta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = ControllerConexionPostgres.getInstance().connection.prepareStatement(llamadaFuncion);

            statement.setString(1, nombreI);
            statement.setString(2, detallesItem);
            statement.setString(3, pathFoto);
            statement.setString(4, subcat);
            statement.setBigDecimal(5, BigDecimal.valueOf(montoIni));
            statement.setString(6, fechaFin);
            statement.setString(7, detalles);
            statement.setString(8, alias);
            statement.setString(9, contrasenna);
            statement.setBigDecimal(10, BigDecimal.valueOf(montoMin));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                codigo = resultSet.getInt(1);
            }
        }
        catch (Exception e){
            System.out.println("No se pudo conectar con la base de datos");
            e.printStackTrace();
            codigo = 0;
        }

        return codigo;
    }


//    public static void main(String[] args) throws ParseException {
//        ControllerAdminPost controllerAdmin = new ControllerAdminPost();
//        String fec = "2021-04-12 7:00:00";
//        int cod = controllerAdmin.iniciarSubasta("Audifonos gato", "Audífonos personalizados con orejas de gato, color azul","src/VIEW/FOTOS_POSTGRESQL/audifonos.jpg", "Artefactos y otros sistemas electronicos", 10000, fec, "Se entrega por correo", "Manchas 2", "cortiJuguete45");
//        System.out.println(cod);
//    }
}