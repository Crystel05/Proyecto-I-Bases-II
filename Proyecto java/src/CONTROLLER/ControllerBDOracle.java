package CONTROLLER;

import MODEL.Item;
import MODEL.Subasta;
import MODEL.Usuario;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ControllerBDOracle {

    public ControllerBDOracle() {}

    public ArrayList<String> obtenerTipoTelefonos () {
        ArrayList<String> telefonos = new ArrayList<String>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARTIPOTELEFONO(?)");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(1);
            while (resultado.next()) {

                telefonos.add(resultado.getString(1));
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return telefonos;
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

    public ArrayList<Usuario> obtenerIdentificacion(boolean esAdmin) {
        ArrayList<Usuario> identidad = new ArrayList<Usuario>();
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
                Usuario usuario = new Usuario();
                usuario.setDocIdent(resultado.getString(1));
                usuario.setNombreApellidos(resultado.getString(2));
                identidad.add(usuario);
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return identidad;
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

    public ArrayList<Integer> obtenerTelefonosUsu (String docIdent) {
        ArrayList<Integer> telefonos = new ArrayList<Integer>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARTELEFONOSUSU(?,?)");
            statement.setString(1, docIdent);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                telefonos.add(resultado.getInt(1));
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return telefonos;
    }

    public void modificarTelefono(String alias, String tipo, int numero, int viejonumero) {
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MODIFICARTELEFONO(?,?,?,?,?)");
            statement.setString(1,alias);
            statement.setInt(2,viejonumero);
            statement.setInt(3,numero);
            statement.setString(4,tipo);
            statement.registerOutParameter(5, OracleTypes.INTEGER);
            statement.execute();

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
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

    public ArrayList<String> getCategorias() {
        ArrayList<String> categorias = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTCATEGORIAS(?)");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(1);
            while(resultado.next()) {
                categorias.add(resultado.getString(1));
            }
        } catch (Exception ex) {
            System.out.println("ERROR!");
            ex.printStackTrace();
        }
        return categorias;
    }

    public ArrayList<String> getSubCategorias(String categoria) {
        ArrayList<String> subcategorias = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTSUBCATEGORIAS(?,?)");
            statement.setString(1,categoria);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(2);
            while(resultado.next()) {
                subcategorias.add(resultado.getString(1));
            }
        } catch (Exception ex) {
            System.out.println("No hay subastas");
        }
        return subcategorias;
    }

    public ArrayList<Subasta> getSubastas() {
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTSUB(?)");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(1);
            while(resultado.next()) {
                Subasta subasta = new Subasta();
                subasta.setNomIt(resultado.getString(1));
                subasta.setFachaFinal(resultado.getDate(2));
                subasta.setMejorMonto(resultado.getFloat(3));
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("ERROR!");
            ex.printStackTrace();
        }
        return subastas;
    }

    public ArrayList<Subasta> getSubastasCat(String categoria) {
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTSUBPORCAT(?,?)");
            statement.setString(1,categoria);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(2);
            while(resultado.next()) {
                Subasta subasta = new Subasta();
                subasta.setNomIt(resultado.getString(1));
                subasta.setFachaFinal(resultado.getDate(2));
                subasta.setMejorMonto(resultado.getFloat(3));
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("No hay subastas");
        }
        return subastas;
    }

    public ArrayList<Subasta> getSubastasSubCat(String subcategoria) {
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTSUBPORSUBCAT(?,?)");
            statement.setString(1,subcategoria);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(2);
            while(resultado.next()) {
                Subasta subasta = new Subasta();
                subasta.setNomIt(resultado.getString(1));
                subasta.setFachaFinal(resultado.getDate(2));
                subasta.setMejorMonto(resultado.getFloat(3));
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("No hay subastas");
        }
        return subastas;
    }





//    public static void main(String[] args){
//        ControllerBDOracle oracle = new ControllerBDOracle();
//        System.out.println(oracle.getSubastasSubCat("Directo del artista"));
//
//    }

}
