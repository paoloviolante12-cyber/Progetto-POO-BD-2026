package gui;

import model.EBook;
import model.Libro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HomeLibreria {
    private JTextField barraDiRicerca;
    private JButton cercaPulsante;
    private JList<String> risultatiList;
    private JLabel scrittaRisultati;
    public JPanel mainPanel;

    private List<Libro> catalogo = new ArrayList<>();

    public HomeLibreria() {
        cercaPulsante.addActionListener(e -> cercaLibri());
        barraDiRicerca.addActionListener(e -> cercaLibri());
    }

    // Metodo per aggiungere libri dall'esterno (es. dal Main)
    public void aggiungiLibro(Libro libro) {
        catalogo.add(libro);
    }

    private void cercaLibri() {
        String testo = barraDiRicerca.getText().trim().toLowerCase();

        if (testo.isEmpty()) {
            scrittaRisultati.setText("Risultati:");
            risultatiList.setListData(new String[0]);
            return;
        }

        List<String> trovati = new ArrayList<>();

        for (Libro libro : catalogo) {
            if (libro.getNomeLibro().toLowerCase().contains(testo) ||
                    libro.getNomeAutore().toLowerCase().contains(testo) ||
                    libro.getCategoria().toLowerCase().contains(testo)) {

                String tipo = (libro instanceof EBook) ? "[EBook]" : "[Cartaceo]";
                trovati.add(tipo + " " + libro.getNomeLibro() +
                        " - " + libro.getNomeAutore() +
                        " | €" + libro.getPrezzoArticolo());
            }
        }

        if (trovati.isEmpty()) {
            scrittaRisultati.setText("Risultati: nessun libro trovato");
        } else {
            scrittaRisultati.setText("Risultati: " + trovati.size() + " libro/i trovato/i");
        }

        risultatiList.setListData(trovati.toArray(new String[0]));
    }
}
