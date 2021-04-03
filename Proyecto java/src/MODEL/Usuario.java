package MODEL;

import java.util.ArrayList;

public class Usuario {

    private String alias;
    private String contrasenna;
    private String docIdent;
    private String nombre;
    private String apellidos;
    private String direccion;
    private ArrayList<Telefono> telefonos;

    public Usuario(String alias, String contrasenna, String docIdent, String nombre, String apellidos, String direccion, ArrayList<Telefono> telefonos) {
        this.alias = alias;
        this.contrasenna = contrasenna;
        this.docIdent = docIdent;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefonos = telefonos;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getDocIdent() {
        return docIdent;
    }

    public void setDocIdent(String docIdent) {
        this.docIdent = docIdent;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}

