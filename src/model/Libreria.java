package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Libreria {
    private String indirizzoNegozio;
    private LocalTime oraroApertura;
    private String nomeNegozio;
    private LocalTime oraroChiusura;
    private List<Cliente> clienti = new ArrayList<>();

    public Libreria(String nomeNegozio, String indirizzoNegozio,
                    LocalTime oraroApertura, LocalTime oraroChiusura) {
        this.nomeNegozio = nomeNegozio;
        this.indirizzoNegozio = indirizzoNegozio;
        this.oraroApertura = oraroApertura;
        this.oraroChiusura = oraroChiusura;
    }

    public boolean aperta() {
        LocalTime adesso = LocalTime.now();
        return adesso.isAfter(oraroApertura) && adesso.isBefore(oraroChiusura);
    }

    public void mostraCatalogo() {
        System.out.println("Catalogo della libreria: " + nomeNegozio);
    }

    public void aggiungiCliente(Cliente c) { clienti.add(c); }
    public List<Cliente> getClienti() { return clienti; }

    public String getNomeNegozio() { return nomeNegozio; }
    public String getIndirizzoNegozio() { return indirizzoNegozio; }
    public LocalTime getOraroApertura() { return oraroApertura; }
    public LocalTime getOraroChiusura() { return oraroChiusura; }
}
