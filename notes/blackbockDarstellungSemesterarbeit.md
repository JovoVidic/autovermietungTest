# 🧱 Blackbox-Architektur – Autovermietungs-System

## 1️⃣ Blackbox-Ansatz

Beim Blackbox-Verfahren betrachten wir jede Komponente nur durch ihre **Eingaben und Ausgaben**, nicht durch die interne Umsetzung.

### Systemübersicht

```
+--------------------+       REST API       +--------------------+       JPA/SQL      +---------------------+
|                    | ------------------> |                    | ------------------> |                     |
|     Frontend       |                     |      Backend       |                     |     Datenbank       |
| (React + TypeScript| <------------------ |  (Spring Boot +    | <------------------ |  PostgreSQL +       |
|  + JS + HTML/CSS)  |    JSON Responses   |   Java 17 + JPA)    |    SQL Queries     |  phpPgAdmin GUI     |
+--------------------+                     +--------------------+                     +---------------------+
```

------

## 2️⃣ Rollen der Komponenten

------

### 🎨 Frontend (React + TypeScript)

**Blackbox-Funktion:** Benutzerinteraktion & Darstellung

**Input:**

- Benutzerdaten (Fahrzeugauswahl, Buchung, Kundendaten)

**Output:**

- API-Requests an das Backend
- Darstellung der Ergebnisse

**Aufgaben:**

- Fahrzeugliste anzeigen
- Fahrzeugdetails anzeigen
- Buchungsformular
- API-Anfragen (GET/POST)
- Validierung (Client-seitig)
- Anzeige von Backend-Daten

------

### 🧠 Backend (Spring Boot + Java 17)

**Blackbox-Funktion:** Business-Logik + Schnittstelle zur Datenbank

**Input:**

- HTTP/JSON vom Frontend

**Output:**

- HTTP/JSON ans Frontend

**Aufgaben:**

- REST Controller (/api/cars, /api/bookings …)
- Services (Verfügbarkeit prüfen, Preis berechnen)
- Validierung
- Fehlerbehandlung
- Logging (Log4j2 / SLF4J)
- Unit-Tests mit JUnit & Mockito
- Datenbankzugriff über JPA/Hibernate

------

### 🗄️ Datenbank (PostgreSQL + phpPgAdmin)

**Blackbox-Funktion:** Persistenz der Daten

**Input:**

- SQL Queries vom Backend (Hibernate/JPA)

**Output:**

- Resultsets zurück ans Backend

**Aufgaben:**

- Speichern von Fahrzeugen, Kunden, Buchungen
- Verfügbarkeits- und Preisabfragen
- Sicherung der Datenintegrität
- Verwaltung via phpPgAdmin

------

## 3️⃣ Blackbox-Flow – Beispiel: Fahrzeug buchen

```
[User klickt "Jetzt buchen"]
       |
       v
+--------------------+
|     Frontend       | ---POST /api/bookings--> JSON--> 
+--------------------+
       |
       v
+--------------------+
|     Backend        | --Prüft Verfügbarkeit, berechnet Preis-->
+--------------------+
       |
       v
+--------------------+
|   Datenbank        | <--Speichert Buchung, Status, Kunde---
+--------------------+
       |
       v
+--------------------+
|     Backend        | <--JSON Response mit Buchungsdaten---
+--------------------+
       |
       v
+--------------------+
|     Frontend       | <--Anzeige Bestätigung---
+--------------------+
```

------

## 4️⃣ Blackbox-Flow – Beispiel: Fahrzeug anzeigen

```
[User öffnet Fahrzeugliste]
       |
       v
+--------------------+
|     Frontend       | ---GET /api/cars--> JSON--> 
+--------------------+
       |
       v
+--------------------+
|     Backend        | --Fragt Autos ab-->
+--------------------+
       |
       v
+--------------------+
|   Datenbank        | <--liefert Autos---
+--------------------+
       |
       v
+--------------------+
|     Backend        | <--JSON ans Frontend---
+--------------------+
       |
       v
+--------------------+
|     Frontend       | <--Anzeige Fahrzeugliste---
+--------------------+
```

------

## 5️⃣ Übersicht der Aufgaben je Komponente

| Komponente    | Aufgaben / Blackbox                                          |
| ------------- | ------------------------------------------------------------ |
| **Frontend**  | Darstellung, Benutzerinteraktion, API-Requests, Validierung, Anzeige |
| **Backend**   | REST-API, Business-Logik, Logging, Validierung, Tests, DB-Kommunikation |
| **Datenbank** | Datenpersistenz, Queries, Integrität, Admin-Interface        |

------

## ASCII-Diagramm – Funktionen & Tools

```
                             +-------------------------+
                             |        Benutzer         |
                             |  (Webbrowser / UI)      |
                             +-------------------------+
                                        |
                                        v
                +-------------------------------------------------+
                |                  Frontend                       |
                |   (React + TypeScript + HTML/CSS, VS Code)      |
                |-------------------------------------------------|
                | Funktionen:                                     |
                | - Fahrzeugliste anzeigen                        |
                | - Fahrzeugdetails anzeigen                      |
                | - Fahrzeug buchen                               |
                | - Kundendaten erfassen                          |
                | - Preisvorschau / Bestätigung                   |
                | - API Requests (GET/POST)                       |
                +-------------------------------------------------+
                                        |
                                        | REST API (HTTP + JSON)
                                        v
                +-------------------------------------------------+
                |                  Backend                        |
                |      (Spring Boot + Java 17, Eclipse IDE)       |
                |-------------------------------------------------|
                | Funktionen:                                     |
                | - REST Controller                               |
                |   * GET /api/cars                               |
                |   * POST /api/bookings                          |
                |   * GET /api/bookings/:id                       |
                | - Services (Business-Logik)                     |
                |   * Verfügbarkeit prüfen                        |
                |   * Preis berechnen                             |
                |   * Buchung validieren                          |
                | - Logging                                       |
                | - Unit-Tests                                    |
                | - JPA/Hibernate                                 |
                +-------------------------------------------------+
                                        |
                                        | SQL / JPA Queries
                                        v
                +-------------------------------------------------+
                |                  Datenbank                      |
                |        (PostgreSQL + phpPgAdmin)                |
                |-------------------------------------------------|
                | Tabellen:                                       |
                | - cars                                           |
                | - customers                                      |
                | - bookings                                       |
                | Funktionen:                                     |
                | - Datenpersistenz                                |
                | - Abfragen / Filter                              |
                | - Integrität & Constraints                       |
                | - Verwaltung via phpPgAdmin                      |
                +-------------------------------------------------+
```

------

## Funktions-Flows – Schritt für Schritt

### 1️⃣ Fahrzeug buchen

- Frontend → POST /api/bookings
- Backend → Validierung, Verfügbarkeit, Preis
- DB → INSERT booking
- Backend → JSON Response
- Frontend → Anzeige der Bestätigung

### 2️⃣ Fahrzeugliste anzeigen

- Frontend → GET /api/cars
- Backend → Service → Repository
- DB → SELECT * FROM cars
- Backend → JSON
- Frontend → Liste rendern

### 3️⃣ Auto anlegen (Admin)

- Frontend → POST /api/cars
- Backend → Validierung + Logging
- DB → INSERT cars
- Backend → Rückgabe ID

### 4️⃣ Buchung stornieren

- Frontend → DELETE /api/bookings/:id
- Backend → Status ändern
- DB → UPDATE booking
- Backend → JSON Bestätigung

------

## Blackbox-Übersicht: Funktionen & Tools

| Funktion / Flow    | Frontend (VS Code)    | Backend (Eclipse)                   | Datenbank (PostgreSQL)                |
| ------------------ | --------------------- | ----------------------------------- | ------------------------------------- |
| Fahrzeug anzeigen  | Liste rendern         | Controller, CarService              | SELECT * FROM cars                    |
| Fahrzeug buchen    | Formular, Validierung | BookingService, Preislogik, Logging | INSERT INTO bookings                  |
| Buchung anzeigen   | Tabelle/Detail        | Controller + Service                | SELECT * FROM bookings                |
| Fahrzeug anlegen   | Admin-Formular        | CarService, Validation              | INSERT INTO cars                      |
| Buchung stornieren | Button/Form           | Service, Logging                    | UPDATE bookings SET status='canceled' |
| Logging            | –                     | Log4j2 / SLF4J                      | –                                     |
| Tests              | –                     | JUnit 5 + Mockito                   | –                                     |

------