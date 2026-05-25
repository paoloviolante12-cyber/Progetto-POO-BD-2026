package Main;

import gui.HomeLibreria;
import model.Cartaceo;
import model.EBook;
import model.Libreria;

import javax.swing.*;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {


        Libreria libreria = new Libreria(
                "La Buona Lettura",
                "Via Roma 1",
                LocalTime.of(9, 0),
                LocalTime.of(20, 0)
        );


        Cartaceo libro1 = new Cartaceo(
                "Il Nome della Rosa", "Umberto Eco", "978-88-452-0000-1",
                502, 1980, 15.99, "Romanzo", "Rigida"
        );
        Cartaceo libro2 = new Cartaceo(
                "1984", "George Orwell", "978-88-000-2222-2",
                328, 1949, 12.99, "Romanzo", "Flessibile"
        );
        EBook libro3 = new EBook(
                "Java per tutti", "Mario Bianchi", "978-88-000-3333-3",
                300, 2022, 9.99, "Informatica", "HTML"
        );
        EBook libro4 = new EBook(
                "Imparare Python", "Luca Verdi", "978-88-000-4444-4",
                250, 2021, 7.99, "Informatica", "Kindle"
        );
        Cartaceo libro5 = new Cartaceo(
                "La Cucina Italiana", "Anna Rossi", "978-88-000-5555-5",
                180, 2019, 19.99, "Cucina", "Rigida"
        );


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(libreria.getNomeNegozio());
            HomeLibreria home = new HomeLibreria();
            home.aggiungiLibro(libro1);
            home.aggiungiLibro(libro2);
            home.aggiungiLibro(libro3);
            home.aggiungiLibro(libro4);
            home.aggiungiLibro(libro5);

            frame.setContentPane(home.mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}