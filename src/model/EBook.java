package model;
import model.Libro;

public class EBook extends Libro{
    public String formatoFile;

    public EBook(String nomeLibro, String nomeAutore, String ISBN, int numeroPagine, int annoRilascio, double prezzoArticolo, String categoria, String formatoFile) {
        super(nomeLibro, nomeAutore, ISBN, numeroPagine, annoRilascio, prezzoArticolo, categoria);
        this.formatoFile = formatoFile;
    }

    public String getFormatoFile() {
        return formatoFile;
    }
}
