package CONTROLLER;
import MODEL.Comentario;
import MODEL.Usuario;
import oracle.jdbc.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import MODEL.Item;
import MODEL.Subasta;

public class ControllerSubasta {

    ControllerSubasta(){}

    public ArrayList<Subasta> getSubastas() {
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTSUB(?)");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(1);
            while(resultado.next()) {
                System.out.println(resultado.getString(1));
                System.out.println(resultado.getDate(2));
                System.out.println(resultado.getFloat(3));
                Item item = new Item();
                Subasta subasta = new Subasta();
                item.setNombre(resultado.getString(1));
                subasta.setFachaFinal(resultado.getDate(2));
                subasta.setMejorMonto(resultado.getFloat(3));
                subasta.setItem(item);
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("ERROR!");
            ex.printStackTrace();
        }
        return subastas;
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

    public ArrayList<Subasta> getSubastasSubCat(String subcategoria) {
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTSUBPORSUBCAT(?,?)");
            statement.setString(1,subcategoria);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(2);
            while(resultado.next()) {
                System.out.println(resultado.getString(1));
                System.out.println(resultado.getDate(2));
                System.out.println(resultado.getFloat(3));
                Item item = new Item();
                Subasta subasta = new Subasta();
                item.setNombre(resultado.getString(1));
                subasta.setFachaFinal(resultado.getDate(2));
                subasta.setMejorMonto(resultado.getFloat(3));
                subasta.setItem(item);
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("No hay subastas");
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
                System.out.println(resultado.getString(1));
                System.out.println(resultado.getDate(2));
                System.out.println(resultado.getFloat(3));
                Item item = new Item();
                Subasta subasta = new Subasta();
                item.setNombre(resultado.getString(1));
                subasta.setFachaFinal(resultado.getDate(2));
                subasta.setMejorMonto(resultado.getFloat(3));
                subasta.setItem(item);
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("No hay subastas");
        }
        return subastas;
    }

    public void realizarSubasta(String nombreItem,  String detallesItem, String imagen, String subcat, float montoIni, Date fechaFinal, String detalles, String alias, String contra) {

        try {
            long date = fechaFinal.getTime();
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL CREARSUBASTA(?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,nombreItem);
            statement.setString(2,detallesItem);
            statement.setString(3,imagen);
            statement.setString(4,subcat);
            statement.setFloat(5,montoIni);
            statement.setTimestamp(6, new java.sql.Timestamp(date));
            statement.setString(7,detalles);
            statement.setString(8,alias);
            statement.setString(9,contra);
            statement.registerOutParameter(10, OracleTypes.INTEGER);
            statement.execute();
        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }

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
                comentario.setComentario(resultado.getString(1));
                comentario.setPuntaje(resultado.getInt(2));
                item.setNombre(resultado.getString(3));
                subasta.setPrecioIni(resultado.getFloat(4));
                subasta.setMejorMonto(resultado.getFloat(5));
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
                System.out.println(resultado.getInt(1));
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return telefonos;
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
                System.out.println(resultado.getString(1));
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return telefonos;
    }

    public ArrayList<String> obtenerAlias() {
        ArrayList<String> alias = new ArrayList<String>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARALIAS(?)");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(1);
            while (resultado.next()) {

                alias.add(resultado.getString(1));
                System.out.println(resultado.getString(1));
            }

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return alias;
    }

    public void modificarUsuario(String aliasOri,  String nombre, String aliasNuevo, String contra, String docIden, String direccion) {
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MODIFICARUSU(?,?,?,?,?,?,?)");
            statement.setString(1,aliasOri);
            statement.setString(2,nombre);
            statement.setString(3,aliasNuevo);
            statement.setString(4,contra);
            statement.setString(5,docIden);
            statement.setString(6,direccion);
            statement.registerOutParameter(7, OracleTypes.INTEGER);
            statement.execute();

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }

    }

    public void modificarTelefono(String alias, String tipo, int numero) {
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MODIFICARTELEFONO(?,?,?,?)");
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

}
