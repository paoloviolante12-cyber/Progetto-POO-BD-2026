-- ==========================================================
-- Progetto-POO-BD-2026 | Script database
-- ==========================================================

-- 1) Creazione database

CREATE DATABASE libreria_db;

-- ================================

-- 2) Inserimento tabelle

CREATE TABLE IF NOT EXISTS acquisti (
    id                  SERIAL PRIMARY KEY,
    numero_transazione  VARCHAR(50)  NOT NULL UNIQUE,
    data_acquisto       DATE         NOT NULL,
    ora_acquisto        TIME         NOT NULL,
    metodo              VARCHAR(50)  NOT NULL,
    prezzo              NUMERIC(10,2) NOT NULL,
    isbn_libro          VARCHAR(30)  NOT NULL,
    nome_libro          VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS prestiti (
    id                  SERIAL PRIMARY KEY,
    ricevuta            VARCHAR(50)  NOT NULL,
    inizio_prestito     DATE         NOT NULL,
    fine_prestito       DATE         NOT NULL,
    isbn_libro          VARCHAR(30)  NOT NULL,
    nome_libro          VARCHAR(255) NOT NULL
);

-- Catalogo libri: sostituisce i dati che prima erano in Main.java
CREATE TABLE IF NOT EXISTS libri (
    id                  SERIAL PRIMARY KEY,
    nome_libro          VARCHAR(255) NOT NULL,
    nome_autore         VARCHAR(255) NOT NULL,
    isbn                VARCHAR(30)  NOT NULL UNIQUE,
    numero_pagine       INT          NOT NULL,
    anno_rilascio       INT          NOT NULL,
    prezzo_articolo     NUMERIC(10,2) NOT NULL,
    categoria           VARCHAR(100) NOT NULL,
    tipo                VARCHAR(20)  NOT NULL CHECK (tipo IN ('Cartaceo', 'EBook')),
    dettaglio           VARCHAR(50)  NOT NULL, -- riferito al tipoCopertina (Cartaceo) o formatoFile (EBook)
    disponibile         BOOLEAN      NOT NULL DEFAULT TRUE
);

INSERT INTO libri (nome_libro, nome_autore, isbn, numero_pagine, anno_rilascio, prezzo_articolo, categoria, tipo, dettaglio) VALUES
('Il Nome della Rosa', 'Umberto Eco', '978-88-452-0000-1', 502, 1980, 15.99, 'Romanzo', 'Cartaceo', 'Rigida'),
('1984', 'George Orwell', '978-88-000-2222-2', 328, 1949, 12.99, 'Romanzo', 'Cartaceo', 'Flessibile'),
('Java per tutti', 'Mario Bianchi', '978-88-000-3333-3', 300, 2022, 9.99, 'Informatica', 'EBook', 'HTML'),
('Imparare Python', 'Luca Verdi', '978-88-000-4444-4', 250, 2021, 7.99, 'Informatica', 'EBook', 'Kindle'),
('La Cucina Italiana', 'Anna Rossi', '978-88-000-5555-5', 180, 2019, 19.99, 'Cucina', 'Cartaceo', 'Rigida'),
('It', 'Stephen King', '978-88-000-6666-6', 1138, 1986, 14.99, 'Horror', 'Cartaceo', 'Flessibile'),
('Dracula', 'Bram Stoker', '978-88-000-7777-7', 418, 1897, 4.99, 'Horror', 'EBook', 'PDF'),
('Il Silenzio degli Innocenti', 'Thomas Harris', '978-88-000-8888-8', 352, 1988, 13.99, 'Thriller', 'Cartaceo', 'Flessibile'),
('Gone Girl', 'Gillian Flynn', '978-88-000-9999-9', 422, 2012, 8.99, 'Thriller', 'EBook', 'Kindle'),
('Il Signore degli Anelli', 'J.R.R. Tolkien', '978-88-100-1010-1', 1216, 1954, 24.99, 'Fantasy', 'Cartaceo', 'Rigida'),
('Harry Potter e la Pietra Filosofale', 'J.K. Rowling', '978-88-100-1111-1', 294, 1997, 6.99, 'Fantasy', 'EBook', 'Kindle'),
('Il Grande Gatsby', 'F. Scott Fitzgerald', '978-88-100-1212-1', 180, 1925, 5.99, 'Romanzo', 'EBook', 'PDF'),
('Cent''anni di Solitudine', 'Gabriel Garcia Marquez', '978-88-100-1313-1', 448, 1967, 16.99, 'Romanzo', 'Cartaceo', 'Flessibile'),
('L''Isola del Tesoro', 'Robert Louis Stevenson', '978-88-100-1414-1', 292, 1883, 10.99, 'Avventura', 'Cartaceo', 'Flessibile'),
('Jurassic Park', 'Michael Crichton', '978-88-100-1515-1', 400, 1990, 8.99, 'Avventura', 'EBook', 'HTML'),
('Dune', 'Frank Herbert', '978-88-100-1616-1', 688, 1965, 11.99, 'Fantascienza', 'EBook', 'Kindle'),
('Fahrenheit 451', 'Ray Bradbury', '978-88-100-1717-1', 158, 1953, 9.99, 'Fantascienza', 'Cartaceo', 'Rigida'),
('Shining', 'Stephen King', '978-88-100-1818-1', 447, 1977, 13.99, 'Horror', 'Cartaceo', 'Rigida'),
('Sale, Grasso, Acido, Calore', 'Samin Nosrat', '978-88-100-1919-1', 480, 2017, 12.99, 'Cucina', 'EBook', 'PDF'),
('Il Codice Da Vinci', 'Dan Brown', '978-88-100-2020-1', 454, 2003, 14.99, 'Thriller', 'Cartaceo', 'Flessibile'),
('Clean Code', 'Robert C. Martin', '978-88-100-2121-1', 464, 2008, 29.99, 'Informatica', 'Cartaceo', 'Rigida'),
('Il Piccolo Principe', 'Antoine de Saint-Exupery', '978-88-100-2222-1', 96, 1943, 3.99, 'Narrativa', 'EBook', 'PDF'),
('Orgoglio e Pregiudizio', 'Jane Austen', '978-88-100-2323-1', 432, 1813, 11.99, 'Romantico', 'Cartaceo', 'Flessibile')
ON CONFLICT (isbn) DO NOTHING;
