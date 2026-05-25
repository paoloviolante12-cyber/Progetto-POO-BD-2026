package gui;

import model.Cartaceo;
import model.Cliente;
import model.EBook;
import model.Libro;
import model.Tessera;
import controller.Prestito;

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
                rigaPrezzo
        };

        dettagliLibro = new JList<>(dettagli);
        dettagliLibro.setEnabled(false);
        mainPanel.add(new JScrollPane(dettagliLibro), BorderLayout.CENTER);

        // Pulsante acquista
        acquistaButton = new JButton("Acquista");
        acquistaButton.addActionListener(e -> {
            double prezzoFinale = (tessera != null && tessera.isValida())
                    ? tessera.applicaSconto(prezzoOriginale)
                    : prezzoOriginale;
            JOptionPane.showMessageDialog(
                    mainPanel,
                    String.format("Hai acquistato: %s\nTotale: €%.2f", libro.getNomeLibro(), prezzoFinale),
                    "Acquisto completato",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(acquistaButton);


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
            if (libro.isDisponibile()) {
                libro.setDisponibile(false);
                Prestito prestito = new Prestito(
                        "RIC-" + libro.getISBN(),
                        java.time.LocalDate.now(),
                        java.time.LocalDate.now().plusDays(30)
                );
                JOptionPane.showMessageDialog(
                        mainPanel,
                        String.format("Hai preso in prestito: %s\nRicevuta: %s\nScadenza restituzione: 30 giorni",
                                libro.getNomeLibro(), prestito.getRicevuta()),
                        "Prestito confermato",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        String.format("Il libro \"%s\" non è attualmente disponibile.", libro.getNomeLibro()),
                        "Non disponibile",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        bottomPanel.add(prestitoButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
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
