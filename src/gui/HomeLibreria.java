package gui;

import model.Cliente;
import model.EBook;
import model.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HomeLibreria {
    private JTextField barraDiRicerca;
    private JButton cercaPulsante;
    private JList<String> risultatiList;
    private JLabel scrittaRisultati;
    private JLabel Account;
    private JButton loginButton;
    private JButton logoutButton;
    public JPanel mainPanel;
    private JComboBox Categoria;
    private JLabel scrittaCategoria;

    private List<Libro> catalogo = new ArrayList<>();
    private List<Libro> risultatiCorrenti = new ArrayList<>();
    private Cliente clienteLoggato = null;

    public HomeLibreria() {
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Account.setText("Ospite");
        loginButton.setVisible(true);
        logoutButton.setVisible(false);

        loginButton.addActionListener(e -> {
            Login.apri(cliente -> {
                clienteLoggato = cliente;
                aggiornaStatoLogin();
                logoutButton.setVisible(true);
            });
        });

        logoutButton.addActionListener(e -> {
            clienteLoggato = null;
            aggiornaStatoLogin();
            logoutButton.setVisible(false);
        });

        // Un solo listener per il pulsante Cerca
        cercaPulsante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cercaLibri();
            }
        });

        barraDiRicerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cercaLibri();
            }
        });

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
        String cat = (String) Categoria.getSelectedItem();
        String testo = barraDiRicerca.getText().trim().toLowerCase();
        risultatiCorrenti.clear();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Libro libro : catalogo) {
            // Se la categoria è vuota (primo elemento) mostra tutte
            boolean matchCategoria = (cat == null || cat.isEmpty())
                    || libro.getCategoria().equalsIgnoreCase(cat);

            boolean matchTesto = testo.isEmpty()
                    || libro.getNomeLibro().toLowerCase().contains(testo)
                    || libro.getNomeAutore().toLowerCase().contains(testo);

            if (matchCategoria && matchTesto) {
                risultatiCorrenti.add(libro);
                String tipo = (libro instanceof EBook) ? "[EBook] " : "[Cartaceo] ";
                model.addElement(tipo + libro.getNomeLibro() + " - " +
                        libro.getNomeAutore() + " | €" + libro.getPrezzoArticolo());
            }
        }

        risultatiList.setModel(model);

        if (model.isEmpty()) {
            scrittaRisultati.setText("Risultati: nessun libro trovato");
        } else {
            scrittaRisultati.setText("Risultati: " + model.size() +
                    " libro/i trovato/i  (doppio click per i dettagli)");
        }
    }
}