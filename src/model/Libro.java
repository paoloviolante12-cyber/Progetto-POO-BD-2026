package model;

public class Libro {
    public String nomeLibro;
    public String nomeAutore;
    public String ISBN;
    public int numeroPagine;
    public int annoRilascio;
    public double prezzoArticolo;
    public String Categoria;

    public Libro(String nomeLibro, String nomeAutore, String ISBN, int numeroPagine, int annoRilascio, double prezzoArticolo, String categoria) {
        this.nomeLibro = nomeLibro;
        this.nomeAutore = nomeAutore;
        this.ISBN = ISBN;
        this.numeroPagine = numeroPagine;
        this.annoRilascio = annoRilascio;
        this.prezzoArticolo = prezzoArticolo;
        Categoria = categoria;
    }

    public String getNomeLibro() {
        return nomeLibro;
    }

    public String getNomeAutore() {
        return nomeAutore;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public int getAnnoRilascio() {
        return annoRilascio;
    }

    public double getPrezzoArticolo() {
        return prezzoArticolo;
    }

    public String getCategoria() {
        return Categoria;
    }
}