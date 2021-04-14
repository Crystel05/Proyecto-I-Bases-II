package MODEL;

import java.math.BigDecimal;
import java.util.Date;

public class tablaComprasXcomprador {

    private String nombreItem;
    private BigDecimal precioInicial;
    private BigDecimal precioFinal;
    private Date fecha;

    public tablaComprasXcomprador(String nombreItem, BigDecimal precioInicial, BigDecimal precioFinal, Date fecha) {
        this.nombreItem = nombreItem;
        this.precioInicial = precioInicial;
        this.precioFinal = precioFinal;
        this.fecha = fecha;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public BigDecimal getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(BigDecimal precioInicial) {
        this.precioInicial = precioInicial;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
