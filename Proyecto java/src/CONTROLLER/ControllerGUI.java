package CONTROLLER;

import java.util.ArrayList;

public class ControllerGUI {

    public ControllerGUI() {}
    private static ControllerGUI controllerGUI;

    private ControllerAdmin controllerAdmin = new ControllerAdmin();
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
            tipos = controllerAdmin.tipos();
        else {
            //oracle llamado de tipos
        }
        return tipos;

    }

    public int agregarTelefono(String alias, String contrasena, int numero, String tipo){
        int codigo = 0;
        if (baseDatoUsada){
            codigo = controllerAdmin.agregarTelefono(alias, contrasena, numero, tipo);
        }
        else{
            //codigo oracle
        }
        return codigo;
    }

    public int registrarUsuario(boolean esAdmin, String alias, String contrasena, String cedula, String nombre, String apellidos, String direccion){
        int codigo = 0;

        if (baseDatoUsada){
            codigo = controllerAdmin.registrarUsusuario(esAdmin, alias, contrasena, cedula, nombre, apellidos, direccion);
        }
        else{
            //llamar al método de oracle
        }
        return codigo;
    }
}
