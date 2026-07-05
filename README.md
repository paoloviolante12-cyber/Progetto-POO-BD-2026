# Progetto-POO-BD-2026
Progetto Università di POO e Base di Dati | Componenti: Paolo Violante (DE1000224) e Luca Prete (DE1000037)
-----------------------------------------------------------------------------------------------------------


# Libreria BCE + DAO

Progetto Java di gestione libreria sviluppato con il pattern **BCE (Boundary-Control-Entity)** e **DAO**, utilizzando **Java Swing** per l'interfaccia grafica e **PostgreSQL** per la persistenza dei dati.

L'applicazione permette di consultare il catalogo, effettuare acquisti e prestiti, gestire l'autenticazione dei clienti e aggiornare automaticamente la disponibilità dei libri nel database.

L'architettura separa chiaramente:

* **Boundary**: interfaccia grafica e interazione con l'utente.
* **Control**: logica di business per acquisti e prestiti.
* **Entity**: rappresentazione dei dati del dominio (libri, clienti, tessere).
* **DAO**: accesso e gestione dei dati tramite JDBC.

Questa struttura rende il progetto facilmente manutenibile, estendibile e conforme ai principi di separazione delle responsabilità.

**Tecnologie utilizzate:** Java, Java Swing, JDBC, PostgreSQL, BCE Pattern, DAO Pattern.
