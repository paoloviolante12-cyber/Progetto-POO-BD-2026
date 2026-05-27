package Main;

import gui.HomeLibreria;
import model.Cartaceo;
import model.EBook;
import model.Libro;
import model.Libreria;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Libreria libreria = new Libreria(
                "La Buona Lettura",
                "Via Roma 1",
                LocalTime.of(9, 0),
                LocalTime.of(20, 0)
        );

        // ArrayList che contiene tutti i libri del catalogo
        ArrayList<Libro> catalogo = new ArrayList<>();

        // Romanzo
        catalogo.add(new Cartaceo(
                "Il Nome della Rosa", "Umberto Eco", "978-88-452-0000-1",
                502, 1980, 15.99, "Romanzo", "Rigida"
        ));
        catalogo.add(new Cartaceo(
                "1984", "George Orwell", "978-88-000-2222-2",
                328, 1949, 12.99, "Romanzo", "Flessibile"
        ));
        // Informatica
        catalogo.add(new EBook(
                "Java per tutti", "Mario Bianchi", "978-88-000-3333-3",
                300, 2022, 9.99, "Informatica", "HTML"
        ));
        catalogo.add(new EBook(
                "Imparare Python", "Luca Verdi", "978-88-000-4444-4",
                250, 2021, 7.99, "Informatica", "Kindle"
        ));
        // Cucina
        catalogo.add(new Cartaceo(
                "La Cucina Italiana", "Anna Rossi", "978-88-000-5555-5",
                180, 2019, 19.99, "Cucina", "Rigida"
        ));
        // Horror
        catalogo.add(new Cartaceo(
                "It", "Stephen King", "978-88-000-6666-6",
                1138, 1986, 14.99, "Horror", "Flessibile"
        ));
        catalogo.add(new EBook(
                "Dracula", "Bram Stoker", "978-88-000-7777-7",
                418, 1897, 4.99, "Horror", "PDF"
        ));
        // Thriller
        catalogo.add(new Cartaceo(
                "Il Silenzio degli Innocenti", "Thomas Harris", "978-88-000-8888-8",
                352, 1988, 13.99, "Thriller", "Flessibile"
        ));
        catalogo.add(new EBook(
                "Gone Girl", "Gillian Flynn", "978-88-000-9999-9",
                422, 2012, 8.99, "Thriller", "Kindle"
        ));
        catalogo.add(new Cartaceo(
                "Il Signore degli Anelli", "J.R.R. Tolkien", "978-88-100-1010-1",
                1216, 1954, 24.99, "Fantasy", "Rigida"
        ));
        catalogo.add(new EBook(
                "Harry Potter e la Pietra Filosofale", "J.K. Rowling", "978-88-100-1111-1",
                294, 1997, 6.99, "Fantasy", "Kindle"
        ));

        // Romanzo
        catalogo.add(new EBook(
                "Il Grande Gatsby", "F. Scott Fitzgerald", "978-88-100-1212-1",
                180, 1925, 5.99, "Romanzo", "PDF"
        ));
        catalogo.add(new Cartaceo(
                "Cent'anni di Solitudine", "Gabriel Garcia Marquez", "978-88-100-1313-1",
                448, 1967, 16.99, "Romanzo", "Flessibile"
        ));

        // Avventura
        catalogo.add(new Cartaceo(
                "L'Isola del Tesoro", "Robert Louis Stevenson", "978-88-100-1414-1",
                292, 1883, 10.99, "Avventura", "Flessibile"
        ));
        catalogo.add(new EBook(
                "Jurassic Park", "Michael Crichton", "978-88-100-1515-1",
                400, 1990, 8.99, "Avventura", "HTML"
        ));

        // Fantascienza
        catalogo.add(new EBook(
                "Dune", "Frank Herbert", "978-88-100-1616-1",
                688, 1965, 11.99, "Fantascienza", "Kindle"
        ));
        catalogo.add(new Cartaceo(
                "Fahrenheit 451", "Ray Bradbury", "978-88-100-1717-1",
                158, 1953, 9.99, "Fantascienza", "Rigida"
        ));

        // Horror
        catalogo.add(new Cartaceo(
                "Shining", "Stephen King", "978-88-100-1818-1",
                447, 1977, 13.99, "Horror", "Rigida"
        ));

        // Cucina
        catalogo.add(new EBook(
                "Sale, Grasso, Acido, Calore", "Samin Nosrat", "978-88-100-1919-1",
                480, 2017, 12.99, "Cucina", "PDF"
        ));

        // Thriller
        catalogo.add(new Cartaceo(
                "Il Codice Da Vinci", "Dan Brown", "978-88-100-2020-1",
                454, 2003, 14.99, "Thriller", "Flessibile"
        ));

        // Informatica
        catalogo.add(new Cartaceo(
                "Clean Code", "Robert C. Martin", "978-88-100-2121-1",
                464, 2008, 29.99, "Informatica", "Rigida"
        ));

        // Narrativa
        catalogo.add(new EBook(
                "Il Piccolo Principe", "Antoine de Saint-Exupery", "978-88-100-2222-1",
                96, 1943, 3.99, "Narrativa", "PDF"
        ));

        // Romantico
        catalogo.add(new Cartaceo(
                "Orgoglio e Pregiudizio", "Jane Austen", "978-88-100-2323-1",
                432, 1813, 11.99, "Romantico", "Flessibile"
        ));


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(libreria.getNomeNegozio());
            HomeLibreria home = new HomeLibreria();

            // Scorre l'ArrayList e aggiunge ogni libro alla home
            for (Libro libro : catalogo) {
                home.aggiungiLibro(libro);
            }

            frame.setContentPane(home.mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}