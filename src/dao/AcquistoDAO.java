package dao;

import controller.Acquisto;
import model.Libro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;


public class AcquistoDAO {

    public void salvaAcquisto(Acquisto acquisto, Libro libro) throws SQLException {
        String sql = "INSERT INTO acquisti " +
                "(numero_transazione, data_acquisto, ora_acquisto, metodo, prezzo, isbn_libro, nome_libro) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, acquisto.getNumeroTransazione());
            stmt.setDate(2, Date.valueOf(acquisto.getDataAcquisto()));
            stmt.setTime(3, Time.valueOf(acquisto.getOraAcquisto()));
            stmt.setString(4, acquisto.getMetodo());
            stmt.setDouble(5, acquisto.pagamentoFinale());
            stmt.setString(6, libro.getISBN());
            stmt.setString(7, libro.getNomeLibro());

            stmt.executeUpdate();
        }
    }
}
