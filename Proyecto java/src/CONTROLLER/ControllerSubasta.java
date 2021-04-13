package CONTROLLER;
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

    public void realizarSubasta(String nombreItem,  String detallesItem, String imagen, String subcat, float montoIni, Date fechaFinal, String detalles, String alias) {

        try {
            long date = fechaFinal.getTime();
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL CREARSUBASTA(?,?,?,?,?,?,?,?,?)");
            statement.setString(1,nombreItem);
            statement.setString(2,detallesItem);
            statement.setString(3,imagen);
            statement.setString(4,subcat);
            statement.setFloat(5,montoIni);
            statement.setTimestamp(6, new java.sql.Timestamp(date));
            statement.setString(7,detalles);
            statement.setString(8,alias);
            statement.registerOutParameter(9, OracleTypes.INTEGER);
            statement.execute();

        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }

    }

}
