package CONTROLLER;

import MODEL.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

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
//                System.out.println(resultado.getInt(1));
//                System.out.println(resultado.getString(2));
//                System.out.println(resultado.getDate(3));
//                System.out.println(resultado.getFloat(4));
//                System.out.println(resultado.getFloat(5));
//                System.out.println(resultado.getString(6));
//                System.out.println(resultado.getString(7));
                Item item = new Item();
                Subasta subasta = new Subasta();
                subasta.setID(resultado.getInt(1));
                item.setNombre(resultado.getString(2));
                subasta.setFachaFinal(resultado.getDate(3));
                subasta.setNomIt(resultado.getString(2));
                subasta.setMejorMonto(resultado.getFloat(4));
                subasta.setPrecioIni(resultado.getFloat(5));
                item.setDetalles(resultado.getString(6));
                item.setPathFoto(resultado.getString(7));
                subasta.setItem(item);
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("ERROR!");
            ex.printStackTrace();
        }
        return subastas;
    } //probar

    public Subasta getDetallesSubastas(int subId) {
        Subasta subasta = new Subasta();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTDETSUB(?,?)");
            statement.setInt(1, subId);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement)statement).getCursor(2);
            while(resultado.next()) {
                System.out.println(resultado.getFloat(1));
                System.out.println(resultado.getString(2));
                System.out.println(resultado.getString(3));
                System.out.println(resultado.getString(4));
                Item item = new Item();
                subasta.setMejorMonto(resultado.getFloat(1));
                item.setDetalles(resultado.getString(2));
                item.setPathFoto(resultado.getString(3));
                subasta.setComprador(resultado.getString(4));
                subasta.setItem(item);
            }
        } catch (Exception ex) {
            System.out.println("ERROR!");
            ex.printStackTrace();
        }
        return subasta;
    } //probar

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
            System.out.println("Error de conexi??n");
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
                Usuario usuario = new Usuario(resultado.getString(1), resultado.getString(2));
                usuarios.add(usuario);
            }
        }
        catch (Exception e){
            System.out.println("Error de conexi??n");
            System.out.println(e);
        }
        return usuarios;
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
                subasta.setFachaFinal(resultado.getDate(4));
                subasta.setPrecioIni(resultado.getFloat(5));
                subasta.setMejorMonto(resultado.getFloat(6));
                subasta.setComprador(resultado.getString(7));
                item.setDetalles(resultado.getString(8));
                subasta.setEnvio(resultado.getString(9));
                subasta.setNomIt(resultado.getString(3));
                item.setPathFoto(resultado.getString(10));

                subasta.setItem(item);
                subasta.setComentario(comentario);
                subastas.add(subasta);
            }
        } catch (Exception ex) {
            System.out.println("Hubo un error!");
            ex.printStackTrace();
        }
        return subastas;
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
            System.out.println("Error de conexi??n");
            System.out.println(e);
            nom = "Error";
        }
        return nom;
    }

    public ArrayList<Subasta> comprasXcomprador(String ident){
        ArrayList<Subasta> compras = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL COMPRASXCOMPRADOR(?,?)");
            statement.setString(1, ident);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                Subasta subasta = new Subasta();
                subasta.setNomIt(resultado.getString(1));
                subasta.setPrecioIni(resultado.getFloat(2));
                subasta.setMejorMonto(resultado.getFloat(3));
                subasta.setFechaInicio(resultado.getTimestamp(4));
                Comentario comentario = new Comentario(resultado.getString(5), resultado.getInt(6));
                Item item = new Item();
                item.setDetalles(resultado.getString(7));
                item.setPathFoto(resultado.getString(9));
                subasta.setEnvio(resultado.getString(8));
                subasta.setComentario(comentario);
                compras.add(subasta);
            }
        }
        catch (Exception e){
            System.out.println("Error de conexi??n");
            System.out.println(e);
        }
        return compras;
    } //llamar y probar

    public String nombreVendedorCxC(String nomItem){
        String nombre = "";
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL mapeoNombreVend(?,?)");
            statement.setString(1, nomItem);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(2);
            while (resultado.next()) {
                nombre = resultado.getString(1);
            }
        }
        catch (Exception e){
            System.out.println("Error de conexi??n");
            System.out.println(e);
        }
        return nombre;

    }

    public boolean realizarSubasta(String nombreItem, String detallesItem, String imagen, String subcat, float montoIni, Date fechaFinal, String detalles, String alias, String contra, float montoMin) {

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
            System.out.println("Error de conexi??n");
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public ArrayList<Subasta> comprasCompradorComentarios(String alias, String pasw){
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARSUBASTASCOMPRADORNOT(?,?,?)");
            statement.setString(1, alias);
            statement.setString(2, pasw);
            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(3);
            while (resultado.next()) {
                Subasta subasta = new Subasta();
                Item item = new Item();
                item.setNombre(resultado.getString(1));
                item.setDetalles(resultado.getString(2));
                item.setPathFoto(resultado.getString(3));
                subasta.setItem(item);
                subasta.setEnvio(resultado.getString(4));
                subastas.add(subasta);
            }
        }
        catch (Exception e){
            System.out.println("Error de conexi??n");
            e.printStackTrace();
        }
        return subastas;
    } //probar

    public ArrayList<Subasta> ventasVendedorComentarios(String alias, String pasw){
        ArrayList<Subasta> subastas = new ArrayList<>();
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL MOSTRARSUBASTASVENDEDORNOT(?,?,?)");
            statement.setString(1, alias);
            statement.setString(2, pasw);
            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(3);
            while (resultado.next()) {
                Subasta subasta = new Subasta();
                Item item = new Item();
                item.setNombre(resultado.getString(1));
                item.setDetalles(resultado.getString(2));
                item.setPathFoto(resultado.getString(3));
                subasta.setItem(item);
                subasta.setEnvio(resultado.getString(4));
                subastas.add(subasta);

            }
        }
        catch (Exception e){
            System.out.println("Error de conexi??n");
            e.printStackTrace();
        }
        return subastas;
    } //probar

    public Integer comentarios(String comentario, int puntacion, boolean esVendedor,
                               boolean compra, String nomItem, String alias, String contra){
        int codigo = 0;
        int esVend;
        int comprar;
        try {
            if(esVendedor)
                esVend = 1;
            else
                esVend = 0;
            if(compra)
                comprar = 1;
            else
                comprar = 0;
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL ENVIARCOMENTARIO(?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, comentario);
            statement.setInt(2, puntacion);
            statement.setInt(3, esVend);
            statement.setInt(4, comprar);
            statement.setString(5, nomItem);
            statement.setString(6, alias);
            statement.setString(7, contra);
            statement.registerOutParameter(8, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(8);
            codigo = 1;
        }
        catch (Exception e){
            System.out.println("Error de conexi??n");
            e.printStackTrace();
            codigo = 0;
        }
        return codigo;
    } //probar

    //3 sps de comentarios

    public static void main(String[] args){
        ControllerBDOracle oracle = new ControllerBDOracle();
        System.out.println(oracle.getSubastasPorUsu("1111155"));

    }

}
