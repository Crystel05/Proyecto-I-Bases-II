package MODEL;

import java.math.BigDecimal;
import java.util.Date;

public class Puja {

    public Puja() {}

    public Puja(BigDecimal monto, Date fecha) {
        this.monto = monto;
        this.fecha = fecha;
    }

    private BigDecimal monto;
    private Date fecha;

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
