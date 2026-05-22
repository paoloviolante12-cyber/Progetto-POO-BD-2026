package controller;

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

    public boolean restituzione() {
        return LocalDate.now().isAfter(finePrestito);
    }
}

