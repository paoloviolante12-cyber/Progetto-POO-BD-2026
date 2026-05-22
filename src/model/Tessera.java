package model;

public class Tessera {
    private String id;
    private String tipo; // "Standard" o "Studente"

    public Tessera(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public boolean isValida() {
        return id != null && !id.isEmpty();
    }

    public double getSconto() {
        if (tipo.equalsIgnoreCase("Studente")) {
            return 0.15; // 15%
        } else {
            return 0.10; // 10% Standard
        }
    }

    public double applicaSconto(double prezzo) {
        return prezzo - (prezzo * getSconto());
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
}