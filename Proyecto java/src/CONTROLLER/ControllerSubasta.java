package CONTROLLER;
import oracle.jdbc.*;
import java.sql.*;
import java.util.ArrayList;
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
            System.out.println("ERROR!");
            ex.printStackTrace();
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
            System.out.println("ERROR!");
            ex.printStackTrace();
        }
        return subastas;
    }

}
