package CONTROLLER;
import MODEL.*;
import oracle.jdbc.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ControllerSubasta {

    ControllerSubasta(){}


    public boolean realizarSubasta(String nombreItem,  String detallesItem, String imagen, String subcat, float montoIni, Date fechaFinal, String detalles, String alias, String contra, float montoMin) {

        try {
            long date = fechaFinal.getTime();
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL CREARSUBASTA(?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,nombreItem);
            statement.setString(2,detallesItem);
            statement.setString(3,imagen);
            statement.setString(4,subcat);
            statement.setFloat(5,montoIni);
            statement.setTimestamp(6, new java.sql.Timestamp(date));
            statement.setString(7,detalles);
            statement.setString(8,alias);
            statement.setString(9,contra);
            statement.setFloat(10,montoMin);
            statement.registerOutParameter(11, OracleTypes.INTEGER);
            ResultSet resultado = (statement.executeQuery());
            int respuesta = statement.getInt(11);
            if (respuesta == 0)
                return true;
        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Subasta> getSubastasPorUsu(String docIdentidad) {
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL SUBASTASUSUARIO(?,?)");
            statement.setString(1,docIdentidad);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(2);
            while(resultado.next()) {
                Comentario comentario = new Comentario();
                Subasta subasta = new Subasta();
                Item item = new Item();
                Usuario usuario = new Usuario();
                comentario.setComentario(resultado.getString(1));
                comentario.setPuntaje(resultado.getInt(2));
                item.setNombre(resultado.getString(3));
                subasta.setFachaFinal(resultado.getDate(4));
                subasta.setPrecioIni(resultado.getFloat(5));
                subasta.setMejorMonto(resultado.getFloat(6));
                usuario.setNombreApellidos(resultado.getString(7));
                item.setDetalles(resultado.getString(8));
                subasta.setEnvio(resultado.getString(9));
                item.setPathFoto(resultado.getString(10));

                subasta.setItem(item);
                subasta.setComentario(comentario);
                subastas.add(subasta);
//                System.out.println(resultado.getString(1));
//                System.out.println(resultado.getInt(2));
//                System.out.println(resultado.getString(3));
//                System.out.println(resultado.getFloat(4));
//                System.out.println(resultado.getFloat(5));
            }
        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return subastas;
    }




    public ArrayList<String> obtenerTipoTelefonos () {
        ArrayList<String> telefonos = new ArrayList<String>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARTIPOTELEFONO(?)");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(1);
            while (resultado.next()) {

                telefonos.add(resultado.getString(1));
                //System.out.println(resultado.getString(1));
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return telefonos;
    }

    public ArrayList<String> obtenerIdentificacion(boolean esAdmin) {
        ArrayList<String> identidad = new ArrayList<String>();
        int esAdministrador = 0;
        if (esAdmin)
            esAdministrador = 1;
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARIDENTIDAD(?,?)");
            statement.setInt(1, esAdministrador);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                identidad.add(resultado.getString(1));
                //System.out.println(resultado.getString(1));
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return identidad;
    }

    public boolean modificarUsuario(String docIdenOri,  String nombre, String alias, String contra, String docIden, String direccion) {
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MODIFICARUSU(?,?,?,?,?,?,?)");
            statement.setString(1,alias);
            statement.setString(2,nombre);
            statement.setString(3,contra);
            statement.setString(4,docIden);
            statement.setString(5,docIdenOri);
            statement.setString(6,direccion);
            statement.registerOutParameter(7, OracleTypes.INTEGER);
            ResultSet resultado = (statement.executeQuery());
            int respuesta = statement.getInt(7);
            if (respuesta == 0)
                return true;
        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return false;
    }



    public void agregarTelefono(String alias, String tipo, int numero) {
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL AGREGARTELEFONO(?,?,?,?)");
            statement.setString(1,alias);
            statement.setInt(2,numero);
            statement.setString(3,tipo);
            statement.registerOutParameter(4, OracleTypes.INTEGER);
            statement.execute();

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }

    }

    public Usuario mostrarInfoUsuario(String docIden) {
        Usuario usuario = new Usuario();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARINFOUSU(?,?)");
            statement.setString(1, docIden);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                System.out.println(resultado.getString(1));
                System.out.println(resultado.getString(2));
                System.out.println(resultado.getString(3));
                System.out.println(resultado.getString(4));
                System.out.println(resultado.getString(5));
                usuario.setContrasenna(resultado.getString(1));
                usuario.setNombreApellidos(resultado.getString(2));
                usuario.setDocIdent(resultado.getString(3));
                usuario.setDireccion(resultado.getString(4));
                usuario.setAlias(resultado.getString(5));
            }
        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return usuario;
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean registrarUsusuario(boolean esAdmin, String alias, String contrasena, String cedula, String nombre, String apellidos, String direccion){
        int entradaBool=0;
        if (esAdmin)
            entradaBool=1;
        try {

            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL registrarusuario(?,?,?,?,?,?,?,?)");

            statement.setInt(1, entradaBool);
            statement.setString(2, alias);
            statement.setString(3, contrasena);
            statement.setString(4, cedula);
            statement.setString(5, nombre);
            statement.setString(6, apellidos);
            statement.setString(7, direccion);
            statement.registerOutParameter(8,OracleTypes.INTEGER);
            statement.execute();
            int respuesta = statement.getInt(8);
            if (respuesta == 0)
                return true;
        }
        catch (Exception e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean iniciarSesion(boolean esAdmin, String alias, String contrasena){
        int entrada = 0;
        if (esAdmin)
            entrada = 1;
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL VERIFICARINICIOSESION(?,?,?)");
            statement.setInt(1, entrada);
            statement.setString(2, alias);
            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(3);
            while (resultado.next()) {
                System.out.println(resultado.getString(1));

                if(resultado.getString(1).equals(contrasena))
                    return true;
            }
        } catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
            return false;
        }
        return false;
    }

    public ArrayList<Puja> pujasXsubasta(String nombreItem){
        ArrayList<Puja> pujas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL PUJASXSUBASTA(?,?)");
            statement.setString(1, nombreItem);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                System.out.println(resultado.getBigDecimal(1));
                System.out.println(resultado.getTimestamp(2));
                Puja puja = new Puja(resultado.getBigDecimal(1), resultado.getTimestamp(2));
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
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARUSUARIOS(?)");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(1);
            while (resultado.next()) {
                System.out.println(resultado.getString(1));
                System.out.println(resultado.getString(2));
                Usuario usuario = new Usuario(resultado.getString(1), resultado.getString(2));
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
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL COMPRASXCOMPRADOR(?,?)");
            statement.setString(1, ident);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                tablaComprasXcomprador item = new tablaComprasXcomprador(resultado.getString(1),
                        resultado.getBigDecimal(2), resultado.getBigDecimal(3), resultado.getTimestamp(4));
                compras.add(item);
            }
        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
        }
        return compras;
    }

    public int pujar(String alias, String pasw, float monto, String itemNombre){

        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL PUJAR(?,?,?,?,?)");
            statement.setString(1, alias);
            statement.setString(2, pasw);
            statement.setFloat(3,monto);
            statement.setString(4, itemNombre);
            statement.registerOutParameter(5, OracleTypes.INTEGER);
            statement.execute();
        }
        catch (Exception e){
            System.out.println("Error de conexión");
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public String cedulaUsuario(String alias, String contra){
        String ced = "";
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL DEVOLVERCEDULA(?,?,?)");
            statement.setString(1, alias);
            statement.setString(2,contra);
            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(3);
            while (resultado.next()) {
                ced = resultado.getString(1);
            }
        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
            ced = "Error";
        }
        return ced;
    }

    public String nombreVendedor(String cedula){
        String nom = "";
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL NOMBREVENDEDOR(?,?)");
            statement.setString(1, cedula);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                nom = resultado.getString(1);
            }
        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
            nom = "Error";
        }
        return nom;
    }
}
