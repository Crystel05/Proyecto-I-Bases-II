package MODEL;

public class Comentario {

    String comentario;
    int puntaje;

    public Comentario(String comentario, int puntaje) {
        this.comentario = comentario;
        this.puntaje = puntaje;
    }
    public Comentario() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
