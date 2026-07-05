package controller;

import dao.LibroDAO;
import dao.PrestitoDAO;
import exception.LibroNonDisponibileException;
import model.Libro;

import java.sql.SQLException;
import java.time.LocalDate;

public class Prestito {
    private String ricevutaPrestito;
    private LocalDate inizioPrestito;
    private LocalDate finePrestito;

    public Prestito(String ricevutaPrestito, LocalDate inizioPrestito, LocalDate finePrestito) {
        this.ricevutaPrestito = ricevutaPrestito;
        this.inizioPrestito = inizioPrestito;
        this.finePrestito = finePrestito;
    }
    public String getRicevuta() { return ricevutaPrestito; }
    public LocalDate getInizioPrestito() { return inizioPrestito; }
    public LocalDate getFinePrestito() { return finePrestito; }
    public boolean restituzione() {
        return LocalDate.now().isAfter(finePrestito);
    }


    public static Prestito eseguiPrestito(Libro libro)
            throws LibroNonDisponibileException, SQLException {

        // Verifica disponibilità (regola di business)
        libro.verificaDisponibilita();

        // Aggiorna lo stato del libro (in memoria e su DB)
        libro.setDisponibile(false);
        new LibroDAO().aggiornaDisponibilita(libro.getISBN(), false);

        // Crea l'Entity Prestito
        Prestito prestito = new Prestito(
                "RIC-" + libro.getISBN(),
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );

        // Prestito tramite il DAO
        new PrestitoDAO().salvaPrestito(prestito, libro);

        return prestito;
    }
}


