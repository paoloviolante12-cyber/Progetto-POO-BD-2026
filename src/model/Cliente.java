
package model;

public class Cliente {
    private String nomeCliente;
    private String cognomeCliente;
    private Tessera tessera;

    public Cliente(String nomeCliente, String cognomeCliente) {
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
        this.tessera = null;
    }

    public Tessera getTessera() { return tessera; }
    public void setTessera(Tessera tessera) { this.tessera = tessera; }

    public String getNomeCliente() { return nomeCliente; }
    public String getCognomeCliente() { return cognomeCliente; }
}
