## 🔹 Verbindung: Blackbox ↔ Spring Boot Klassen

| Blackbox-Komponente | Spring Boot Layer / Klassen  | Aufgabe / Mapping                                            |
| ------------------- | ---------------------------- | ------------------------------------------------------------ |
| **Frontend**        | React + TypeScript (VS Code) | Benutzerinteraktion, Formulare, GET/POST Requests            |
| **Backend**         | Controller                   | `AutoController` → REST-Endpoints (`GET /api/autos`, `POST /api/autos` …) |
|                     | Service                      | `AutoService` → Business-Logik, Berechnungen, Validierung, Aufrufe an Repository |
|                     | Repository                   | `AutoRepository` → Datenzugriff über JPA/Hibernate           |
| **Datenbank**       | Entity / Model               | `Auto.java` → DB-Tabelle `autos`, Mapping von Feldern auf Spalten |

------

## 🔹 Blackbox-Flows & Klassen

### 1️⃣ Fahrzeugliste anzeigen (`GET /api/autos`)

**Blackbox Flow:** Frontend → Backend → DB → Backend → Frontend

**Mapping zu Klassen:**

```
Frontend → AutoController.getAllAutos() → AutoService.getAllAutos() → AutoRepository.findAll() → DB: SELECT * FROM autos → AutoService → AutoController → JSON → Frontend
```

### 2️⃣ Fahrzeug anlegen (`POST /api/autos`)

```
Frontend (Formular) → AutoController.neuesAuto(@RequestBody Auto) → AutoService.createAuto(auto) → AutoRepository.save(auto) → DB: INSERT INTO autos → AutoService → AutoController → JSON Response → Frontend
```

### 3️⃣ Auto aktualisieren (`PUT /api/autos/{id}`)

```
Frontend → AutoController.updateAuto() → AutoService.updateAuto() → AutoRepository.findById() + save() → DB → AutoService → Controller → JSON → Frontend
```

### 4️⃣ Auto löschen (`DELETE /api/autos/{id}`)

```
Frontend → AutoController.deleteAuto() → AutoService.deleteAuto() → AutoRepository.deleteById() → DB → AutoService → Controller → HTTP Status → Frontend
```

------

## 🔹 Wer erzeugt Instanzen & DB-Verbindung?

| Komponente           | Wer erzeugt / verwaltet                                  |
| -------------------- | -------------------------------------------------------- |
| Controller           | Spring Boot (Dependency Injection via `@RestController`) |
| Service              | Spring Boot (`@Service`)                                 |
| Repository           | Spring Boot (`@Repository` + Spring Data JPA)            |
| Entity / Model       | JPA/Hibernate (wird instanziert bei Zugriff auf DB)      |
| Datenbank-Connection | Spring Boot DataSource (über `application.properties`)   |

💡 **Merke:** Du musst **niemals selbst `new` für Controller/Service/Repository** schreiben – Spring erledigt alles. Nur Entities (`Auto`) werden beim Anlegen / Persistieren erzeugt.



[ Benutzer / Frontend ]
       |
       | GET /api/autos
       v
+----------------------------+
| AutoController (Controller)|
| - getAllAutos()            |
+----------------------------+
       |
       v
+----------------------------+
| AutoService (Service)      |
| - getAllAutos()            |
| - Business-Logik           |
+----------------------------+
       |
       v
+----------------------------+
| AutoRepository (Repository)|
| - findAll() (JPA)          |
+----------------------------+
       |
       v
+----------------------------+
| PostgreSQL Datenbank       |
| - Tabelle: autos           |
| - SQL: SELECT * FROM autos |
+----------------------------+
       |
       ^ JSON Response (Liste von Autos)
       |
[ Frontend / UI ]
- Anzeige Fahrzeugliste
🔹 Erklärung des Flows
Frontend: sendet HTTP GET Request an /api/autos.

Controller (AutoController): empfängt Request, ruft Service auf.

Service (AutoService): enthält die Business-Logik, z.B. Filtern, Validierung, Preisberechnung, ruft Repository auf.

Repository (AutoRepository): führt den JPA-Aufruf findAll() aus → SQL Query an DB.

Datenbank: liefert Resultset zurück → Repository → Service → Controller → JSON Response.

Frontend: rendert Fahrzeugliste anhand der JSON-Daten.

💡 Merke:

Controller = Schnittstelle nach außen

Service = interne Business-Logik

Repository = Schnittstelle zur DB

Entity (Auto) = Abbildung DB-Tabelle

Spring Boot DI sorgt dafür, dass Controller/Service/Repository automatisch instanziiert werden.