package dao;

import model.Cartaceo;
import model.EBook;
import model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public List<Libro> caricaCatalogo() throws SQLException {
        List<Libro> catalogo = new ArrayList<>();
        String sql = "SELECT * FROM libri ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Libro libro;
                String tipo = rs.getString("tipo");
                String dettaglio = rs.getString("dettaglio");

                if ("EBook".equalsIgnoreCase(tipo)) {
                    libro = new EBook(
                            rs.getString("nome_libro"),
                            rs.getString("nome_autore"),
                            rs.getString("isbn"),
                            rs.getInt("numero_pagine"),
                            rs.getInt("anno_rilascio"),
                            rs.getDouble("prezzo_articolo"),
                            rs.getString("categoria"),
                            dettaglio
                    );
                } else {
                    libro = new Cartaceo(
                            rs.getString("nome_libro"),
                            rs.getString("nome_autore"),
                            rs.getString("isbn"),
                            rs.getInt("numero_pagine"),
                            rs.getInt("anno_rilascio"),
                            rs.getDouble("prezzo_articolo"),
                            rs.getString("categoria"),
                            dettaglio
                    );
                }

                libro.setDisponibile(rs.getBoolean("disponibile"));
                catalogo.add(libro);
            }
        }
        return catalogo;
    }

    public void aggiornaDisponibilita(String isbn, boolean disponibile) throws SQLException {
        String sql = "UPDATE libri SET disponibile = ? WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, disponibile);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        }
    }
}
