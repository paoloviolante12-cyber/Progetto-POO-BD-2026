package model;
import model.Libro;

public class Cartaceo extends Libro{

    public String tipoCopertina;

    public Cartaceo(String nomeLibro, String nomeAutore, String ISBN, int numeroPagine, int annoRilascio, double prezzoArticolo, String categoria, String tipoCopertina) {
        super(nomeLibro, nomeAutore, ISBN, numeroPagine, annoRilascio, prezzoArticolo, categoria);
        this.tipoCopertina = tipoCopertina;
    }

    public String getTipoCopertina() {
        return tipoCopertina;
    }
}
