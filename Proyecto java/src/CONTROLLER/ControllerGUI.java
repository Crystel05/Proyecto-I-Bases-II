package CONTROLLER;

import MODEL.Puja;
import MODEL.Subasta;
import MODEL.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControllerGUI {

    public ControllerGUI() {}
    private static ControllerGUI controllerGUI;

    private final ControllerBDPostgre controllerBDPostgre = new ControllerBDPostgre();
    private final ControllerInicioSesionPostgre inicioSesion = new ControllerInicioSesionPostgre();
    private final ControllerBDOracle oracle = new ControllerBDOracle();
    private final ControllerInicioSesionOracle oracleInicio = new ControllerInicioSesionOracle();

    public static ControllerGUI getInstance(){
        if (controllerGUI == null){
            controllerGUI = new ControllerGUI();
        }
        return controllerGUI;
    }

    private boolean baseDatoUsada;
    private String alias;
    private String contrasena;
    private ArrayList<Subasta> subastasActivas;

    public boolean isBaseDatoUsada() {
        return baseDatoUsada;
    }

    public void setBaseDatoUsada(boolean baseDatoUsada) {
        this.baseDatoUsada = baseDatoUsada;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<Subasta> getSubastasActivas() {
        return subastasActivas;
    }

    public void setSubastasActivas() {
        if(baseDatoUsada)
            this.subastasActivas = controllerBDPostgre.mostrarSubastasActivas();
        else{
            this.subastasActivas = oracle.getSubastas();
        }
    }

    //-----------------------------------------------------------------

    //Inicio de sesión

    public int verificarInicioSesion(boolean esAdmin, String alias, String contra){
        int codigo;
        System.out.println(baseDatoUsada);
        if(baseDatoUsada) {
            codigo = inicioSesion.iniciarSesion(esAdmin, alias, contra);
        }
        else {

            if(oracleInicio.iniciarSesion(esAdmin, alias, contra))
                codigo = 1;
            else
                codigo = 0;
        }
        return codigo;
    } //listo

    //Reguistrar Usuario

    public ArrayList<String> cargarTipos(){
        ArrayList<String> tipos = null;
        if(baseDatoUsada)
            tipos = controllerBDPostgre.tipos();
        else {
            tipos = oracle.obtenerTipoTelefonos();
        }
        return tipos;

    } //listo

    public int agregarTelefono(String alias, String contrasena, int numero, String tipo){
        int codigo = 0;
        if (baseDatoUsada){
            codigo = controllerBDPostgre.agregarTelefono(alias, contrasena, numero, tipo);
        }
        else{
            oracle.agregarTelefono(alias, tipo, numero);
        }
        return codigo;
    }

    public int registrarUsuario(boolean esAdmin, String alias, String contrasena, String cedula, String nombre, String apellidos, String direccion){
        int codigo;

        if (baseDatoUsada){
            codigo = controllerBDPostgre.registrarUsusuario(esAdmin, alias, contrasena, cedula, nombre, apellidos, direccion);
        }
        else{
            if(oracle.registrarUsusuario(esAdmin, alias, contrasena, cedula, nombre, apellidos, direccion))
                codigo = 1;
            else
                codigo = 0;
        }
        return codigo;
    } //listo

    //Modificar Usuario

    public ArrayList<Usuario> mostrarUsuariosEditar(boolean esAdmin){
        ArrayList<Usuario> usuarios;

        if(baseDatoUsada){
            usuarios = controllerBDPostgre.mostrarUsuariosEditar(esAdmin);
        }
        else {
            usuarios = oracle.obtenerIdentificacion(esAdmin);
        }
        return usuarios;
    } //listo

    public Usuario mostrarInfoEd(String ced){
        Usuario usuario;
        if (baseDatoUsada){
            usuario = controllerBDPostgre.mostrarInfo(ced);
        }
        else{
            usuario = oracle.mostrarInfoUsuario(ced);
        }
        return usuario;
    } //listo

    public ArrayList<Integer> mostrarTelsUs(String ced){
        ArrayList<Integer> num;
        if(baseDatoUsada){
            num = controllerBDPostgre.mostrarTelsU(ced);
        }
        else {
            num = oracle.obtenerTelefonosUsu(ced);
        }
        return num;
    } //listo

    public Integer modificarTel(String alias, int telV, int telN, String tipo){
        int cod;
        if(baseDatoUsada){
            cod = controllerBDPostgre.modificarTel(telV, telN, tipo);
        }
        else {
            oracle.modificarTelefono(alias, tipo, telN, telV);
            cod = 1;
        }
        return cod;
    } //listo

    public Integer modificarUsuario(String cedulaVieja, String nombre, String aliasNuevo, String contra, String ced, String dir){
        int cod = 0;
        if(baseDatoUsada){
            cod = controllerBDPostgre.modificarUsuario(cedulaVieja, nombre, aliasNuevo, contra, ced, dir);
        }
        else {
            if(oracle.modificarUsuario(cedulaVieja, nombre, aliasNuevo, contra, ced, dir))
                cod = 1;
            else
                cod = 0;
        }
        return cod;
    } //listo

    //Subastas activas

    public ArrayList<String> categorias () {
        ArrayList<String> cats;

        if (baseDatoUsada) {
            cats = controllerBDPostgre.categorias();
        } else {
            cats = oracle.getCategorias();
        }

        return cats;
    } //listo

    public ArrayList<String> subCategorias (String categoria) {
        ArrayList<String> subCats;

        if (baseDatoUsada) {
            subCats = controllerBDPostgre.subCategorias(categoria);
        } else {
            subCats = oracle.getSubCategorias(categoria);
        }

        return subCats;
    } //listo

    public ArrayList<Subasta> subastasTablaSin () {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.mostrarSubastasActivas();
        } else {
            subastas = oracle.getSubastas();
        }

        return subastas;
    } //listo

    public ArrayList<Subasta> subastasCategoria (String categoria) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.subastasActivasCategoria(categoria);
        } else {
            subastas = oracle.getSubastasCat(categoria);
        }

        return subastas;
    } // listo

    public ArrayList<Subasta> subastasFinal (String categoria, String subCategoria) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.subastasActivasFinal(categoria, subCategoria);
        } else {
            subastas = oracle.getSubastasSubCat(subCategoria);
        }

        return subastas;
    } //listo

    public Subasta detallesSubas (int subastaID) {
        Subasta subasta;

        if (baseDatoUsada) {
            subasta = controllerBDPostgre.detallesSubasta(subastaID);
        } else {
            subasta = oracle.getDetallesSubastas(subastaID);
        }
        return subasta;
    } //probar

    //Pujas por subasta

    public ArrayList<String> nombreSubastas(){
        setSubastasActivas();
        ArrayList<String> nombres = new ArrayList<>();

        for(Subasta subasta: subastasActivas){
            nombres.add(subasta.getItem().getNombre());
        }

        return nombres;
    } //listo

    public ArrayList<Puja> pujasXsubasta(String nombreItem){
        ArrayList<Puja> pujas;
        if(baseDatoUsada){
            pujas = controllerBDPostgre.pujasXsubasta(nombreItem);
        }
        else{
            pujas = oracle.pujasXsubasta(nombreItem);
        }
        return pujas;
    } //listo

    //Ventas por vendedor

    public ArrayList<Usuario> usuariosMostrar(){
        ArrayList<Usuario> cedulas;
        if(baseDatoUsada){
            cedulas = controllerBDPostgre.mostrarUsuarios();
        }
        else{
            cedulas = oracle.mostrarUsuarios();
        }
        return cedulas;
    } //listo

    public ArrayList<Subasta> subastasXvendedor (String docIdent) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.subastasXvendedor(docIdent);
        } else {
            subastas = oracle.getSubastasPorUsu(docIdent);
        }

        return subastas;
    } //listo

    public String nombreVend (String docIdent){
        String nom;
        if (baseDatoUsada){
            nom = controllerBDPostgre.nombreVendedor(docIdent);
        }
        else {
            nom = oracle.nombreVendedor(docIdent);
        }
        return nom;
    } //listo

    //Compras por comprador

    public ArrayList<Subasta> llenarTablaComprasComprador(String indent){
        System.out.println("haol");
        ArrayList<Subasta> compras = new ArrayList<>();
        if(baseDatoUsada){
            compras = controllerBDPostgre.comprasXcomprador(indent);
        }
        else{
            ArrayList<Subasta> subastas = oracle.comprasXcomprador(indent);
            for(Subasta s: subastas){
                s.setVendedor(oracle.nombreVendedorCxC(s.getNomIt()));
                compras.add(s);
            }
        }
        for(Subasta s: compras){
            System.out.println(1);
            s.getVendedor();
        }
        return compras;
    }

    //Iniciar subasta

    public Integer iniciarSubasta(String nombre, String detallesItem, String pathFoto, String subcat, float montoIni, String fechaFin,
                                  String detalles, float monMin) throws ParseException {
        int cod;
        if(baseDatoUsada){
            cod = controllerBDPostgre.iniciarSubasta(nombre, detallesItem, pathFoto, subcat, montoIni, fechaFin, detalles, alias, contrasena, monMin);
        }
        else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d hh:mm:ss");
            Date fecha = format.parse(fechaFin);

            if(oracle.realizarSubasta(nombre, detallesItem, pathFoto, subcat, montoIni, fecha, detalles, alias, contrasena, monMin))
                cod = 1;
            else
                cod = 0;
        }
        return cod;
    } //listo

    //Pujar por ítem

    public int pujar(float monto, String itemN){
        int codigo = 0;
        if(baseDatoUsada){
            codigo = controllerBDPostgre.pujar(alias, contrasena, monto, itemN);
        }
        else {
            codigo = oracle.pujar(alias, contrasena, monto, itemN);
        }
        return codigo;
    }

    //Comentarios

    public ArrayList<Subasta> subastasCompras(){
        ArrayList<Subasta> subastas;
        if(baseDatoUsada){
            subastas = controllerBDPostgre.subastasComprador(alias, contrasena); //(alias, contrasena);
        }
        else {
            subastas = oracle.comprasCompradorComentarios(alias, contrasena);
        }
        return subastas;
    }

    public ArrayList<Subasta> subastasVentas(){
        ArrayList<Subasta> subastas;
        if(baseDatoUsada){
            subastas = controllerBDPostgre.subastasVendedor(alias, contrasena);//(alias, contrasena);
        }
        else {
            subastas = oracle.ventasVendedorComentarios(alias, contrasena);
        }
        return subastas;
    }

    public Integer comentarios(String comentario, int puntacion, boolean esVendedor,
                               boolean compra, String nomItem){
        int cod;

        if(baseDatoUsada){
            cod = controllerBDPostgre.comentarios(comentario, puntacion, esVendedor, compra, nomItem, alias, contrasena);
        }
        else{
            cod = oracle.comentarios(comentario, puntacion, esVendedor, compra, nomItem, alias, contrasena);
        }

        return cod;
    }
}
