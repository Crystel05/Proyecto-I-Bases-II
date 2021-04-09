package MODEL;

public class Item {

    private String categoria;
    private String subcategoria;
    private String nombre;
    private String detalles;
    private String pathFoto;

    public Item(String categoria, String subcategoria, String nombre, String detalles, String pathFoto) {
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.nombre = nombre;
        this.detalles = detalles;
        this.pathFoto = pathFoto;
    }
    public Item(String nombre) {
        this.nombre = nombre;
    }
    public Item() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }
}