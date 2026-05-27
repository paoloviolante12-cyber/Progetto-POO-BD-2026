package gui;

import model.Cliente;
import model.EBook;
import model.Libro;

import javax.swing.*;
import java.awt.*;
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
    public JPanel mainPanel;
    private JComboBox Categoria;
    private JLabel scrittaCategoria;

    private List<Libro> catalogo = new ArrayList<>();
    private List<Libro> risultatiCorrenti = new ArrayList<>();
    private Cliente clienteLoggato = null;

    public HomeLibreria() {
        // Ricostruisce il layout da codice per avere la scrollbar funzionante
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // === RIGA IN ALTO: barra di ricerca + categoria + bottone cerca ===
        JPanel topPanel = new JPanel(new BorderLayout(5, 0));
        topPanel.add(barraDiRicerca, BorderLayout.CENTER);

        JPanel categoriaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        categoriaPanel.add(scrittaCategoria);
        categoriaPanel.add(Categoria);
        categoriaPanel.add(cercaPulsante);
        topPanel.add(categoriaPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // === CENTRO: label risultati + lista con scrollbar ===
        JPanel centerPanel = new JPanel(new BorderLayout(0, 5));
        centerPanel.add(scrittaRisultati, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(risultatiList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // === RIGA IN BASSO: account + bottone login ===
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(Account, BorderLayout.WEST);
        bottomPanel.add(loginButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // === LISTENER ===
        Account.setText("Ospite");
        aggiornaStatoLogin();

        cercaPulsante.addActionListener(e -> cercaLibri());
        barraDiRicerca.addActionListener(e -> cercaLibri());
        Categoria.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
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
        for (ActionListener al : loginButton.getActionListeners()) {
            loginButton.removeActionListener(al);
        }

        if (clienteLoggato != null) {
            String tessera = (clienteLoggato.getTessera() != null)
                    ? " [" + clienteLoggato.getTessera().getTipo() + "]"
                    : "";
            Account.setText(clienteLoggato.getNomeCliente() +
                    " " + clienteLoggato.getCognomeCliente() + tessera);
            loginButton.setText("Logout");
            loginButton.addActionListener(e -> {
                clienteLoggato = null;
                aggiornaStatoLogin();
            });
        } else {
            Account.setText("Ospite");
            loginButton.setText("Login");
            loginButton.addActionListener(e -> {
                Login.apri(cliente -> {
                    clienteLoggato = cliente;
                    aggiornaStatoLogin();
                });
            });
        }
    }

    public void aggiungiLibro(Libro libro) {
        catalogo.add(libro);
        cercaLibri();
    }

    private void cercaLibri() {
        String cat = (String) Categoria.getSelectedItem();
        String testo = barraDiRicerca.getText().trim().toLowerCase();
        risultatiCorrenti.clear();
        DefaultListModel<String> model = new DefaultListModel<>();

        boolean filtrando = (cat != null && !cat.isEmpty()) || !testo.isEmpty();

        for (Libro libro : catalogo) {
            boolean matchCategoria = (cat == null || cat.isEmpty())
                    || libro.getCategoria().equalsIgnoreCase(cat);

            boolean matchTesto = testo.isEmpty()
                    || libro.getNomeLibro().toLowerCase().contains(testo)
                    || libro.getNomeAutore().toLowerCase().contains(testo);

            if (matchCategoria && matchTesto) {
                risultatiCorrenti.add(libro);
                String tipo = (libro instanceof EBook) ? "[EBook] " : "[Cartaceo] ";
                String disp = libro.isDisponibile() ? "" : " [Non disponibile]";
                model.addElement(tipo + libro.getNomeLibro() + " - " +
                        libro.getNomeAutore() + " | €" + libro.getPrezzoArticolo() + disp);
            }
        }

        risultatiList.setModel(model);

        if (!filtrando) {
            scrittaRisultati.setText("Catalogo completo: " + model.size() + " libri  (doppio click per i dettagli)");
        } else if (model.isEmpty()) {
            scrittaRisultati.setText("Risultati: nessun libro trovato");
        } else {
            scrittaRisultati.setText("Risultati: " + model.size() +
                    " libro/i trovato/i  (doppio click per i dettagli)");
        }
    }
}