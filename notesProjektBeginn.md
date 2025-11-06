## 🧾 **Projektübersicht: Autovermietung Backend mit Spring Boot**

### ✅ **Technologien & Anforderungen**

* **Backend:** Java + Spring Boot + Maven
* **Persistenz:** PostgreSQL-Datenbank
* **Frontend:** React (noch nicht begonnen)
* **Tests:** Unit-Tests mit JUnit/Mockito
* **Logging:** SLF4J + Logback
* **Dokumentation:** Swagger UI + Markdown-Dateien

***

### 🧱 **Schritte, die du umgesetzt hast**

#### 1. **Projektstruktur erstellt**

* Package: `ch.juventus.autovermietung`
* Unterordner: `controller`, `service`, `repository`, `model`, `config`, `exception`

#### 2. **Entität `Auto` erstellt**

* Felder: `id`, `marke`, `modell`, `kennzeichen`, `verfuegbar`, `preisProTag`
* Fehler im Getter wurde behoben (`return preisProTag;`)

#### 3. **Repository für `Auto`**

* Interface `AutoRepository` mit `JpaRepository`

#### 4. **Service für `Auto`**

* Methoden: `getAlleAutos()`, `neuesAutoHinzufuegen(Auto)`
* Logging integriert

#### 5. **Controller für `Auto`**

* Endpunkte: `GET /api/autos`, `POST /api/autos`

#### 6. **PostgreSQL-Datenbank angebunden**

* Datenbankname: `autovermietung`
* Benutzer: `postgres`, Passwort: `fghz`
* Verbindung erfolgreich, Tabelle `auto` wurde automatisch erstellt

#### 7. **Test erfolgreich durchgeführt**

* Auto per `POST` hinzugefügt
* `GET` zeigt gespeicherte Autos → Persistenz funktioniert

***

### 🛠️ **Tools & Umgebung**

* Entwicklungsumgebung: **Eclipse**
* Datenbankverwaltung: **pgAdmin 4**
* SQL-Abfragen über das **Query Tool** in pgAdmin

***

### 📌 Nächste mögliche Schritte

* Swagger UI einrichten
* Weitere Entitäten (`Kunde`, `Reservierung`)
* Unit-Tests schreiben
* Fehlerbehandlung (z. B. `404` bei nicht gefundenen Autos)
* Dokumentation in Markdown beginnen

***
