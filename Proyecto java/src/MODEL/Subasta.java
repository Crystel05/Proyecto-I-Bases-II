package MODEL;
import java.util.Date;

public class Subasta {

    private int ID;
    private String vendedor;
    private String comprador;
    private String envio;
    private float precioIni;
    private float mejorMonto;
    private Date fechaInicio;
    private Date fachaFinal;
    private Item item;
    private String nomIt;
    private Comentario comentario;

    public Subasta(String vendedor, String envio, float precioIni, float mejorMonto, Date fechaInicio, Date fachaFinal, Item item, Comentario comentario) {
        this.vendedor = vendedor;
        this.envio = envio;
        this.precioIni = precioIni;
        this.mejorMonto = mejorMonto;
        this.fechaInicio = fechaInicio;
        this.fachaFinal = fachaFinal;
        this.item = item;
        this.comentario = comentario;
    }

    public Subasta() {
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNomIt() {
        return nomIt;
    }

    public void setNomIt(String nomIt) {
        this.nomIt = nomIt;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public float getPrecioIni() {
        return precioIni;
    }

    public void setPrecioIni(float precioIni) {
        this.precioIni = precioIni;
    }

    public float getMejorMonto() {
        return mejorMonto;
    }

    public void setMejorMonto(float mejorMonto) {
        this.mejorMonto = mejorMonto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFachaFinal() {
        return fachaFinal;
    }

    public void setFachaFinal(Date fachaFinal) {
        this.fachaFinal = fachaFinal;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
