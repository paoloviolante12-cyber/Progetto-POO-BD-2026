package exception;

public class LibroNonDisponibileException extends Exception {

    private String nomeLibro;

    public LibroNonDisponibileException(String nomeLibro) {
        super("Il libro \"" + nomeLibro + "\" non è disponibile: copie terminate.");
        this.nomeLibro = nomeLibro;
    }

    public String getNomeLibro() {
        return nomeLibro;
    }
}