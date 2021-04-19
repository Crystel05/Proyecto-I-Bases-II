package CONTROLLER;

import MODEL.Puja;
import MODEL.Subasta;
import MODEL.Usuario;
import MODEL.tablaComprasXcomprador;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ControllerGUI {

    public ControllerGUI() {}
    private static ControllerGUI controllerGUI;

    private ControllerAdminPost controllerAdminPost = new ControllerAdminPost();
    private ControllerInicioSesion inicioSesion = new ControllerInicioSesion();

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
            this.subastasActivas = controllerAdminPost.mostrarSubastasActivas();
        //else
            //oracle lista
    }

    public int verificarInicioSesion(boolean esAdmin, String alias, String contra){
        int codigo = 0;
        System.out.println(baseDatoUsada);
        if(baseDatoUsada) {
            codigo = inicioSesion.iniciarSesion(esAdmin, alias, contra);
        }
        else {
            //llamar funcionOracle
        }
        return codigo;
    }

    public ArrayList<String> cargarTipos(){
        ArrayList<String> tipos = null;
        if(baseDatoUsada)
            tipos = controllerAdminPost.tipos();
        else {
            //oracle llamado de tipos
        }
        return tipos;

    }

    public int agregarTelefono(String alias, String contrasena, int numero, String tipo){
        int codigo = 0;
        if (baseDatoUsada){
            codigo = controllerAdminPost.agregarTelefono(alias, contrasena, numero, tipo);
        }
        else{
            //codigo oracle
        }
        return codigo;
    }

    public int registrarUsuario(boolean esAdmin, String alias, String contrasena, String cedula, String nombre, String apellidos, String direccion){
        int codigo = 0;

        if (baseDatoUsada){
            codigo = controllerAdminPost.registrarUsusuario(esAdmin, alias, contrasena, cedula, nombre, apellidos, direccion);
        }
        else{
            //llamar al método de oracle
        }
        return codigo;
    }

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
            codigo = controllerAdminPost.pujar(alias, contrasena, monto, itemN);
        }
        else {
            //oracle
        }
        return codigo;
    }

    public ArrayList<String> nombresSubastas(){
        ArrayList<String> nombres;
        if (baseDatoUsada){
            nombres = controllerAdminPost.nombreSubastas();
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
            pujas = controllerAdminPost.pujasXsubasta(nombreItem);
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
            cedulas = controllerAdminPost.mostrarUsuarios();
        }
        else{
            cedulas = new ArrayList<>();
        }
        return cedulas;
    }

    public ArrayList<Subasta> llenarTablaComprasComprador(String indent){
        ArrayList<Subasta> compras;
        if(baseDatoUsada){
            compras = controllerAdminPost.comprasXcomprador(indent);
        }
        else{
            compras = new ArrayList<>();
        }
        return compras;
    }

    public ArrayList<Usuario> mostrarUsuariosEditar(boolean esAdmin){
        ArrayList<Usuario> usuarios;

        if(baseDatoUsada){
            usuarios = controllerAdminPost.mostrarUsuariosEditar(esAdmin);
        }
        else {
            usuarios = new ArrayList<>();
        }
        return usuarios;
    }

    public Usuario mostrarInfoEd(String ced){
        Usuario usuario;
        if (baseDatoUsada){
            usuario = controllerAdminPost.mostrarInfo(ced);
        }
        else{
            usuario = new Usuario();
        }
        return usuario;
    }

    public ArrayList<Integer> mostrarTelsUs(String ced){
        ArrayList<Integer> num;
        if(baseDatoUsada){
            num = controllerAdminPost.mostrarTelsU(ced);
        }
        else {
            num = new ArrayList<>();
        }
        return num;
    }

    public Integer modificarTel(int telV, int telN, String tipo){
        int cod;
        if(baseDatoUsada){
            cod = controllerAdminPost.modificarTel(telV, telN, tipo);
        }
        else {
            cod = 0;
        }
        return cod;
    }

    public Integer modificarUsuario(String cedulaVieja, String nombre, String aliasNuevo, String contra, String ced, String dir){
        int cod = 0;
        if(baseDatoUsada){
            cod = controllerAdminPost.modificarUsuario(cedulaVieja, nombre, aliasNuevo, contra, ced, dir);
        }
        else {
            cod = 0;
        }
        return cod;
    }

    public ArrayList<String> categorias () {
        ArrayList<String> cats;

        if (baseDatoUsada) {
            cats = controllerAdminPost.categorias();
        } else {
            cats = new ArrayList<>();
        }

        return cats;
    }

    public ArrayList<String> subCategorias (String categoria) {
        ArrayList<String> subCats;

        if (baseDatoUsada) {
            subCats = controllerAdminPost.subCategorias(categoria);
        } else {
            subCats = new ArrayList<>();
        }

        return subCats;
    }

    public ArrayList<Subasta> subastasTablaSin () {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerAdminPost.mostrarSubastasActivas();
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public ArrayList<Subasta> subastasCategoria (String categoria) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerAdminPost.subastasActivasCategoria(categoria);
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public ArrayList<Subasta> subastasFinal (String categoria, String subCategoria) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerAdminPost.subastasActivasFinal(categoria, subCategoria);
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public Subasta detallesSubas (int subastaID) {
        Subasta subasta;

        if (baseDatoUsada) {
            subasta = controllerAdminPost.detallesSubasta(subastaID);
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
            cod = controllerAdminPost.iniciarSubasta(nombre, detallesItem, pathFoto, subcat, montoIni, fechaFin, detalles, alias, contrasena, monMin);
        }
        else {
            cod = 0;
        }
        return cod;
    }

    public ArrayList<Subasta> subastasXvendedor (String docIdent) {
        ArrayList<Subasta> subastas;

        if (baseDatoUsada) {
            subastas = controllerAdminPost.subastasXvendedor(docIdent);
        } else {
            subastas = new ArrayList<>();
        }

        return subastas;
    }

    public String nombreVend (String docIdent){
        String nom;
        if (baseDatoUsada){
            nom = controllerAdminPost.nombreVendedor(docIdent);
        }
        else {
            nom = "";
        }
        return nom;
    }

    public String devolverCedulaUser(){
        String ced;
        if (baseDatoUsada){
            ced = controllerAdminPost.cedulaUsuario(alias, contrasena);
        }
        else {
            ced = "";
        }
        return ced;
    }

    public Integer comentarios(String comentario, int puntacion, boolean esVendedor,
                               boolean compra, String nomItem){
        int cod;

        if(baseDatoUsada){
            cod = controllerAdminPost.comentarios(comentario, puntacion, esVendedor, compra, nomItem, alias, contrasena);
        }
        else{
            cod = 0;
        }

        return cod;
    }
}
