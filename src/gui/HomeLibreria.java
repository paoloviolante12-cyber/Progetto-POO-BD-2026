package gui;

import model.Cliente;
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
    private JLabel Account;
    private JButton loginButton;
    public JPanel mainPanel;

    private List<Libro> catalogo = new ArrayList<>();
    private List<Libro> risultatiCorrenti = new ArrayList<>();
    private Cliente clienteLoggato = null;

    public HomeLibreria() {
        // Aggiunge margini interni al pannello principale
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Stato iniziale: ospite
        Account.setText("Ospite");
        loginButton.setVisible(true);

        loginButton.addActionListener(e -> {
            Login.apri(cliente -> {
                clienteLoggato = cliente;
                aggiornaStatoLogin();
            });
        });

        cercaPulsante.addActionListener(e -> cercaLibri());
        barraDiRicerca.addActionListener(e -> cercaLibri());

        risultatiList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = risultatiList.getSelectedIndex();
                    if (index >= 0 && index < risultatiCorrenti.size()) {
                        InterfacciaLibro.apri(risultatiCorrenti.get(index), clienteLoggato);
                    }
                }
            }
        });
    }

    private void aggiornaStatoLogin() {
        if (clienteLoggato != null) {
            String tessera = (clienteLoggato.getTessera() != null)
                    ? " [" + clienteLoggato.getTessera().getTipo() + "]"
                    : "";
            Account.setText(clienteLoggato.getNomeCliente() +
                    " " + clienteLoggato.getCognomeCliente() + tessera);
            loginButton.setVisible(false);
        } else {
            Account.setText("Ospite");
            loginButton.setVisible(true);
        }
    }

    public void aggiungiLibro(Libro libro) {
        catalogo.add(libro);
    }

    private void cercaLibri() {
        String testo = barraDiRicerca.getText().trim().toLowerCase();
        risultatiCorrenti.clear();

        if (testo.isEmpty()) {
            scrittaRisultati.setText("Risultati:");
            risultatiList.setListData(new String[0]);
            return;
        }

        for (Libro libro : catalogo) {
            if (libro.getNomeLibro().toLowerCase().contains(testo) ||
                    libro.getNomeAutore().toLowerCase().contains(testo) ||
                    libro.getCategoria().toLowerCase().contains(testo)) {
                risultatiCorrenti.add(libro);
            }
        }

        if (risultatiCorrenti.isEmpty()) {
            scrittaRisultati.setText("Risultati: nessun libro trovato");
            risultatiList.setListData(new String[0]);
        } else {
            scrittaRisultati.setText("Risultati: " + risultatiCorrenti.size() +
                    " libro/i trovato/i  (doppio click per i dettagli)");
            String[] nomi = risultatiCorrenti.stream()
                    .map(l -> ((l instanceof EBook) ? "[EBook] " : "[Cartaceo] ") +
                            l.getNomeLibro() + " - " + l.getNomeAutore() +
                            " | €" + l.getPrezzoArticolo())
                    .toArray(String[]::new);
            risultatiList.setListData(nomi);
        }
    }
}