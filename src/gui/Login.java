package gui;

import model.Cliente;
import model.Tessera;

import javax.swing.*;
import java.awt.*;

public class Login {
    public JPanel mainPanel;
    private JButton accediButton;
    private JButton registratiButton;

    public interface LoginListener {
        void onLogin(Cliente cliente);
    }

    public Login(LoginListener listener) {
        mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField campoNome = new JTextField();
        JTextField campoCognome = new JTextField();

        // Combo per tipo tessera
        JComboBox<String> tipoTessera = new JComboBox<>(new String[]{"Standard", "Studente"});

        JPanel nomePanel = new JPanel(new BorderLayout(5, 0));
        nomePanel.add(new JLabel("Nome:    "), BorderLayout.WEST);
        nomePanel.add(campoNome, BorderLayout.CENTER);

        JPanel cognomePanel = new JPanel(new BorderLayout(5, 0));
        cognomePanel.add(new JLabel("Cognome:"), BorderLayout.WEST);
        cognomePanel.add(campoCognome, BorderLayout.CENTER);

        JPanel tesseraPanel = new JPanel(new BorderLayout(5, 0));
        tesseraPanel.add(new JLabel("Tessera: "), BorderLayout.WEST);
        tesseraPanel.add(tipoTessera, BorderLayout.CENTER);

        accediButton = new JButton("Accedi");
        registratiButton = new JButton("Registrati");

        mainPanel.add(nomePanel);
        mainPanel.add(cognomePanel);
        mainPanel.add(tesseraPanel);
        mainPanel.add(accediButton);
        mainPanel.add(registratiButton);

        accediButton.addActionListener(e -> eseguiLogin(campoNome, campoCognome, tipoTessera, listener));
        registratiButton.addActionListener(e -> eseguiLogin(campoNome, campoCognome, tipoTessera, listener));
    }

    private void eseguiLogin(JTextField campoNome, JTextField campoCognome,
                             JComboBox<String> tipoTessera,
                             LoginListener listener) {
        String nome = campoNome.getText().trim();
        String cognome = campoCognome.getText().trim();


        if (nome.isEmpty() || cognome.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Inserisci nome e cognome.", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, cognome);
        String tipo = (String) tipoTessera.getSelectedItem();

        if (listener != null) listener.onLogin(cliente);

        SwingUtilities.getWindowAncestor(mainPanel).dispose();
    }

    public static void apri(LoginListener listener) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login(listener).mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 280);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}