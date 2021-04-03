package MODEL;

public class Telefono {

    private int numero;
    private Tipo tipo;

    public Telefono(int numero, Tipo tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
