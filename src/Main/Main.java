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


        // Romanzo
        Cartaceo libro1 = new Cartaceo(
                "Il Nome della Rosa", "Umberto Eco", "978-88-452-0000-1",
                502, 1980, 15.99, "Romanzo", "Rigida"
        );
        Cartaceo libro2 = new Cartaceo(
                "1984", "George Orwell", "978-88-000-2222-2",
                328, 1949, 12.99, "Romanzo", "Flessibile"
        );
        // Informatica
        EBook libro3 = new EBook(
                "Java per tutti", "Mario Bianchi", "978-88-000-3333-3",
                300, 2022, 9.99, "Informatica", "HTML"
        );
        EBook libro4 = new EBook(
                "Imparare Python", "Luca Verdi", "978-88-000-4444-4",
                250, 2021, 7.99, "Informatica", "Kindle"
        );
        // Cucina
        Cartaceo libro5 = new Cartaceo(
                "La Cucina Italiana", "Anna Rossi", "978-88-000-5555-5",
                180, 2019, 19.99, "Cucina", "Rigida"
        );
        // Horror
        Cartaceo libro6 = new Cartaceo(
                "It", "Stephen King", "978-88-000-6666-6",
                1138, 1986, 14.99, "Horror", "Flessibile"
        );
        EBook libro7 = new EBook(
                "Dracula", "Bram Stoker", "978-88-000-7777-7",
                418, 1897, 4.99, "Horror", "PDF"
        );
        // Thriller
        Cartaceo libro8 = new Cartaceo(
                "Il Silenzio degli Innocenti", "Thomas Harris", "978-88-000-8888-8",
                352, 1988, 13.99, "Thriller", "Flessibile"
        );
        EBook libro9 = new EBook(
                "Gone Girl", "Gillian Flynn", "978-88-000-9999-9",
                422, 2012, 8.99, "Thriller", "Kindle"
        );
        // Romantico



        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(libreria.getNomeNegozio());
            HomeLibreria home = new HomeLibreria();
            home.aggiungiLibro(libro1);
            home.aggiungiLibro(libro2);
            home.aggiungiLibro(libro3);
            home.aggiungiLibro(libro4);
            home.aggiungiLibro(libro5);
            home.aggiungiLibro(libro6);
            home.aggiungiLibro(libro7);
            home.aggiungiLibro(libro8);
            home.aggiungiLibro(libro9);

            frame.setContentPane(home.mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}