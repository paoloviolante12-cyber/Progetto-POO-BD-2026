package gui;

import model.Cartaceo;
import model.Cliente;
import model.EBook;
import model.Libro;
import model.Tessera;
import controller.Prestito;
import exception.LibroNonDisponibileException;

import javax.swing.*;
import java.awt.*;

public class InterfacciaLibro {
    public JPanel mainPanel;
    private JLabel nomeLibro;
    private JList<String> dettagliLibro;
    private JButton acquistaButton;
    private JButton prestitoButton;

    public InterfacciaLibro(Libro libro, Cliente cliente) {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Titolo
        nomeLibro = new JLabel(libro.getNomeLibro());
        nomeLibro.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(nomeLibro, BorderLayout.NORTH);

        // Dettagli
        String tipo = (libro instanceof EBook) ? "EBook" : "Cartaceo";
        String extra = (libro instanceof EBook)
                ? "Formato:     " + ((EBook) libro).getFormatoFile()
                : "Copertina:   " + ((Cartaceo) libro).getTipoCopertina();

        // Calcolo prezzo con eventuale sconto
        double prezzoOriginale = libro.getPrezzoArticolo();
        Tessera tessera = (cliente != null) ? cliente.getTessera() : null;

        String rigaPrezzo;
        if (tessera != null && tessera.isValida()) {
            double prezzoScontato = tessera.applicaSconto(prezzoOriginale);
            rigaPrezzo = String.format(
                    "Prezzo:      €%.2f  →  €%.2f (%s: -%d%%)",
                    prezzoOriginale, prezzoScontato,
                    tessera.getTipo(), (int)(tessera.getSconto() * 100)
            );
        } else {
            rigaPrezzo = String.format("Prezzo:      €%.2f", prezzoOriginale);
        }

        String[] dettagli = {
                "Autore:      " + libro.getNomeAutore(),
                "ISBN:        " + libro.getISBN(),
                "Categoria:   " + libro.getCategoria(),
                "Tipo:        " + tipo,
                extra,
                "Pagine:      " + libro.getNumeroPagine(),
                "Anno:        " + libro.getAnnoRilascio(),
                rigaPrezzo,
                "Disponibile: " + (libro.isDisponibile() ? "Sì" : "No")
        };

        dettagliLibro = new JList<>(dettagli);
        dettagliLibro.setEnabled(false);
        mainPanel.add(new JScrollPane(dettagliLibro), BorderLayout.CENTER);

        // ========== BOTTONE ACQUISTA con try-catch ==========
        acquistaButton = new JButton("Acquista");
        acquistaButton.addActionListener(e -> {

            try {
                // Verifica se il libro è disponibile — se no, lancia l'eccezione
                libro.verificaDisponibilita();

                // Se arriviamo qui, il libro è disponibile
                libro.setDisponibile(false);

                double prezzoFinale = (tessera != null && tessera.isValida())
                        ? tessera.applicaSconto(prezzoOriginale)
                        : prezzoOriginale;

                JOptionPane.showMessageDialog(
                        mainPanel,
                        String.format("Hai acquistato: %s\nTotale: €%.2f",
                                libro.getNomeLibro(), prezzoFinale),
                        "Acquisto completato",
                        JOptionPane.INFORMATION_MESSAGE
                );

                aggiornaDettagli(libro, tessera, prezzoOriginale, tipo, extra);

            } catch (LibroNonDisponibileException ex) {
                // L'eccezione viene catturata qui
                JOptionPane.showMessageDialog(
                        mainPanel,
                        ex.getMessage(),
                        "Errore - Libro Non Disponibile",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(acquistaButton);

        // ========== BOTTONE PRESTITO con try-catch ==========
        prestitoButton = new JButton("Prestito");
        prestitoButton.addActionListener(e -> {
            if (libro instanceof EBook) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Gli EBook non sono disponibili per il prestito.",
                        "Prestito non disponibile",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            try {
                // Verifica se il libro è disponibile — se no, lancia l'eccezione
                libro.verificaDisponibilita();

                // Se arriviamo qui, il libro è disponibile
                libro.setDisponibile(false);

                Prestito prestito = new Prestito(
                        "RIC-" + libro.getISBN(),
                        java.time.LocalDate.now(),
                        java.time.LocalDate.now().plusDays(30)
                );

                JOptionPane.showMessageDialog(
                        mainPanel,
                        String.format("Hai preso in prestito: %s\nRicevuta: %s\nScadenza: 30 giorni",
                                libro.getNomeLibro(), prestito.getRicevuta()),
                        "Prestito confermato",
                        JOptionPane.INFORMATION_MESSAGE
                );

                aggiornaDettagli(libro, tessera, prezzoOriginale, tipo, extra);

            } catch (LibroNonDisponibileException ex) {
                // L'eccezione viene catturata qui
                JOptionPane.showMessageDialog(
                        mainPanel,
                        ex.getMessage(),
                        "Errore - Libro Non Disponibile",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        bottomPanel.add(prestitoButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Aggiorna la JList dei dettagli dopo un acquisto/prestito
    private void aggiornaDettagli(Libro libro, Tessera tessera,
                                  double prezzoOriginale, String tipo, String extra) {
        String rigaPrezzo;
        if (tessera != null && tessera.isValida()) {
            double prezzoScontato = tessera.applicaSconto(prezzoOriginale);
            rigaPrezzo = String.format("Prezzo:      €%.2f  →  €%.2f (%s: -%d%%)",
                    prezzoOriginale, prezzoScontato,
                    tessera.getTipo(), (int)(tessera.getSconto() * 100));
        } else {
            rigaPrezzo = String.format("Prezzo:      €%.2f", prezzoOriginale);
        }

        String[] dettagliAggiornati = {
                "Autore:      " + libro.getNomeAutore(),
                "ISBN:        " + libro.getISBN(),
                "Categoria:   " + libro.getCategoria(),
                "Tipo:        " + tipo,
                extra,
                "Pagine:      " + libro.getNumeroPagine(),
                "Anno:        " + libro.getAnnoRilascio(),
                rigaPrezzo,
                "Disponibile: " + (libro.isDisponibile() ? "Sì" : "No")
        };
        dettagliLibro.setListData(dettagliAggiornati);
    }

    public static void apri(Libro libro, Cliente cliente) {
        JFrame frame = new JFrame("Dettagli Libro");
        frame.setContentPane(new InterfacciaLibro(libro, cliente).mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}