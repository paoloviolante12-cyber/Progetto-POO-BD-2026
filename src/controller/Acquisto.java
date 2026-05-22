
package controller;

import java.time.LocalDate;
import java.time.LocalTime;

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
}

