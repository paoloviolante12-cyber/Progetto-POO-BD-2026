package model;

public class Tessera {
    private String id;
    private String tipo;

    public Tessera(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public boolean isValida() {
        return id != null && !id.isEmpty();
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
}

