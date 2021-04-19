package CONTROLLER;

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
                //System.out.println(resultado.getString(1));
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
}
