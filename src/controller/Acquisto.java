
package controller;

import dao.AcquistoDAO;
import exception.LibroNonDisponibileException;
import model.Cliente;
import model.Libro;
import model.Tessera;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Acquisto {
    private String numeroTransazione;
    private LocalDate dataAcquisto;
    private LocalTime oraAcquisto;
    private String metodo;
    private double prezzo;

    public Acquisto(String numeroTransazione, LocalDate dataAcquisto,
                    LocalTime oraAcquisto, String metodo, double prezzo) {
        this.numeroTransazione = numeroTransazione;
        this.dataAcquisto = dataAcquisto;
        this.oraAcquisto = oraAcquisto;
        this.metodo = metodo;
        this.prezzo = prezzo;
    }

    public double pagamentoFinale() { return prezzo; }

    public String getScontrino() {
        return "Transazione: " + numeroTransazione +
                " | Data: " + dataAcquisto +
                " | Metodo: " + metodo +
                " | Totale: €" + prezzo;
    }

    public String getNumeroTransazione() { return numeroTransazione; }
    public LocalDate getDataAcquisto() { return dataAcquisto; }
    public LocalTime getOraAcquisto() { return oraAcquisto; }
    public String getMetodo() { return metodo; }

    // Classe che gestisce l'acquisto di un libro.
    // Collega l'interfaccia grafica ai dati dell'acquisto e al database.

    public static Acquisto eseguiAcquisto(Libro libro, Cliente cliente, String metodo)
            throws LibroNonDisponibileException, SQLException {

        // Verifica disponibilità (regola di business, lancia eccezione se non disponibile)
        libro.verificaDisponibilita();


        // Calcolo prezzo, con sconto tessera se presente e valida
        double prezzoOriginale = libro.getPrezzoArticolo();
        Tessera tessera = (cliente != null) ? cliente.getTessera() : null;
        double prezzoFinale = (tessera != null && tessera.isValida())
                ? tessera.applicaSconto(prezzoOriginale)
                : prezzoOriginale;

        // Aggiorna lo stato del libro (in memoria e su DB)
        libro.setDisponibile(false);
        new dao.LibroDAO().aggiornaDisponibilita(libro.getISBN(), false);

        // Crea l'Entity Acquisto
        Acquisto acquisto = new Acquisto(
                "TRX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                LocalDate.now(),
                LocalTime.now().withNano(0),
                metodo,
                prezzoFinale
        );

        new AcquistoDAO().salvaAcquisto(acquisto, libro);

        return acquisto;
    }
}
