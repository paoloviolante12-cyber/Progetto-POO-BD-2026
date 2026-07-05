package dao;

import controller.Prestito;
import model.Libro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrestitoDAO {

    public void salvaPrestito(Prestito prestito, Libro libro) throws SQLException {
        String sql = "INSERT INTO prestiti " +
                "(ricevuta, inizio_prestito, fine_prestito, isbn_libro, nome_libro) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prestito.getRicevuta());
            stmt.setDate(2, Date.valueOf(prestito.getInizioPrestito()));
            stmt.setDate(3, Date.valueOf(prestito.getFinePrestito()));
            stmt.setString(4, libro.getISBN());
            stmt.setString(5, libro.getNomeLibro());

            stmt.executeUpdate();
        }
    }
}
