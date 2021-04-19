package CONTROLLER;

import MODEL.Puja;
import MODEL.Subasta;
import MODEL.Usuario;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        //else
        //oracle lista
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
            usuarios = oracle.;
        }
        return usuarios;
    }

    public Usuario mostrarInfoEd(String ced){
        Usuario usuario;
        if (baseDatoUsada){
            usuario = controllerBDPostgre.mostrarInfo(ced);
        }
        else{
            usuario = oracle.mostrarInfoUsuario(ced);
        }
        return usuario;
    }

    public ArrayList<Integer> mostrarTelsUs(String ced){
        ArrayList<Integer> num;
        if(baseDatoUsada){
            num = controllerBDPostgre.mostrarTelsU(ced);
        }
        else {
            num = new ArrayList<>();
        }
        return num;
    }

    public Integer modificarTel(int telV, int telN, String tipo){
        int cod;
        if(baseDatoUsada){
            cod = controllerBDPostgre.modificarTel(telV, telN, tipo);
        }
        else {
            cod = 0;
        }
        return cod;
    }

    public Integer modificarUsuario(String cedulaVieja, String nombre, String aliasNuevo, String contra, String ced, String dir){
        int cod = 0;
        if(baseDatoUsada){
            cod = controllerBDPostgre.modificarUsuario(cedulaVieja, nombre, aliasNuevo, contra, ced, dir);
        }
        else {
            cod = 0;
        }
        return cod;
    }
//----------------

    public ArrayList<String> nombreSubastas(){
        setSubastasActivas();
        ArrayList<String> nombres = new ArrayList<>();

        for(Subasta subasta: subastasActivas){
            nombres.add(subasta.getItem().getNombre());
        }

        return nombres;
    }

    public int pujar(float monto, String itemN){
        int codigo = 0;
        if(baseDatoUsada){
            codigo = controllerBDPostgre.pujar(alias, contrasena, monto, itemN);
        }
        else {
            //oracle
        }
        return codigo;
    }

    public ArrayList<String> nombresSubastas(){
        ArrayList<String> nombres;
        if (baseDatoUsada){
            nombres = controllerBDPostgre.nombreSubastas();
        }
        else {
            //oracle
            nombres = new ArrayList<>();
        }
        return nombres;
    }

    public ArrayList<Puja> pujasXsubasta(String nombreItem){
        ArrayList<Puja> pujas;
        if(baseDatoUsada){
            pujas = controllerBDPostgre.pujasXsubasta(nombreItem);
        }
        else{
            //oracle
            pujas = new ArrayList<>();
        }
        return pujas;
    }

    public ArrayList<Usuario> usuariosMostrar(){
        ArrayList<Usuario> cedulas;
        if(baseDatoUsada){
            cedulas = controllerBDPostgre.mostrarUsuarios();
        }
        else{
            cedulas = new ArrayList<>();
        }
        return cedulas;
    }

    public ArrayList<Subasta> llenarTablaComprasComprador(String indent){
        ArrayList<Subasta> compras;
        if(baseDatoUsada){
            compras = controllerBDPostgre.comprasXcomprador(indent);
        }
        else{
            compras = new ArrayList<>();
        }
        return compras;
    }


    public ArrayList<String> categorias () {
        ArrayList<String> cats;

        if (baseDatoUsada) {
            cats = controllerBDPostgre.categorias();
        } else {
            cats = new ArrayList<>();
        }

        return cats;
    }

    public ArrayList<String> subCategorias (String categoria) {
        ArrayList<String> subCats;

        if (baseDatoUsada) {
            subCats = controllerBDPostgre.subCategorias(categoria);
        } else {
            subCats = new ArrayList<>();
        }

        return subCats;
    }

    public ArrayList<Subasta> subastasTablaSin () {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.mostrarSubastasActivas();
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public ArrayList<Subasta> subastasCategoria (String categoria) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.subastasActivasCategoria(categoria);
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public ArrayList<Subasta> subastasFinal (String categoria, String subCategoria) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.subastasActivasFinal(categoria, subCategoria);
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public Subasta detallesSubas (int subastaID) {
        Subasta subasta;

        if (baseDatoUsada) {
            subasta = controllerBDPostgre.detallesSubasta(subastaID);
        } else {
            subasta = new Subasta();
        }

        return subasta;
    }

    public Integer iniciarSubasta(String nombre, String detallesItem, String pathFoto, String subcat, float montoIni, String fechaFin,
                                  String detalles, float monMin){
        int cod;
        if(baseDatoUsada){
            System.out.println(alias);
            System.out.println(contrasena);
            cod = controllerBDPostgre.iniciarSubasta(nombre, detallesItem, pathFoto, subcat, montoIni, fechaFin, detalles, alias, contrasena, monMin);
        }
        else {
            cod = 0;
        }
        return cod;
    }

    public ArrayList<Subasta> subastasXvendedor (String docIdent) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerBDPostgre.subastasXvendedor(docIdent);
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public String nombreVend (String docIdent){
        String nom;
        if (baseDatoUsada){
            nom = controllerBDPostgre.nombreVendedor(docIdent);
        }
        else {
            nom = "";
        }
        return nom;
    }

    public ArrayList<Subasta> subastasCompras(){
        ArrayList<Subasta> subastas;
        if(baseDatoUsada){
            subastas = controllerBDPostgre.subastasComprador(alias, contrasena); //(alias, contrasena);
        }
        else {
            subastas = new ArrayList<>();
        }
        return subastas;
    }

    public ArrayList<Subasta> subastasVentas(){
        ArrayList<Subasta> subastas;
        if(baseDatoUsada){
            subastas = controllerBDPostgre.subastasVendedor(alias, contrasena);//(alias, contrasena);
        }
        else {
            subastas = new ArrayList<>();
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
            cod = 0;
        }

        return cod;
    }
}
