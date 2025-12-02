# 🧱 **1. Was ist eine Entity (@Entity)?**

- `@Entity` markiert eine Java-Klasse als **Datenbanktabelle**.
- Jede Instanz der Klasse entspricht einem **Datensatz** in dieser Tabelle.
- Deine Klasse `Auto` wird zu einer Tabelle `auto`.

------

# 🔑 **2. Was bedeutet @Id & @GeneratedValue?**

- `@Id` ist der **Primärschlüssel** (eindeutiger Bezeichner pro Objekt).
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` sagt:
   - Die Datenbank erzeugt die ID automatisch (Auto-Increment).

------

# 🧩 **3. Wie wird ein neues Auto erzeugt?**

```
Auto auto = new Auto(null, "Audi", "A4", ...);
```

- erzeugt ein **Java-Objekt im Arbeitsspeicher (RAM)**.
- noch **nicht** in der Datenbank!

Erst durch:

```
autoRepository.save(auto);
```

wird es in der Datenbank gespeichert.

------

# 🗄️ **4. Rolle des AutoRepository**

```
public interface AutoRepository extends JpaRepository<Auto, Long> {}
```

Das Repository:

- stellt Standardmethoden bereit: `save()`, `findAll()`, `deleteById()` …
- erzeugt automatisch SQL-Befehle via Hibernate
- du schreibst **kein SQL** selbst

------

# 🧠 **5. Rolle des AutoService**

Der Service ist für die **Geschäftslogik** zuständig:

✔ Regeln
 ✔ Validierungen
 ✔ Berechnungen
 ✔ Verknüpfen von Daten
 ✔ Aufruf des Repositories

Beispiele:

- prüfen, ob ein Auto verfügbar ist
- Mietpreis berechnen
- logische Entscheidungen

❌ kein Zugriff auf die Außenwelt
 ❌ keine HTTP-Anfragen

------

# 🌍 **6. Rolle des AutoController**

Der Controller:

✔ ist die **Schnittstelle zur Außenwelt** (Browser, Postman, Frontend)
 ✔ empfängt HTTP-Anfragen
 ✔ ruft den Service auf
 ✔ gibt Antworten zurück (JSON)

Beispiele:

- GET /autos
- POST /autos
- DELETE /autos/1

------

# 🔗 **7. So hängen die Klassen zusammen**

## **Datenfluss (von außen nach innen):**

```
HTTP-Anfrage
    ↓
AutoController     (nimmt Anfrage entgegen)
    ↓
AutoService        (Geschäftslogik)
    ↓
AutoRepository     (Datenbankzugriff)
    ↓
Datenbank (auto)
```

## **Datenfluss (beim Speichern eines Autos):**

```
POST /autos
    ↓
Controller → Service → Repository → SQL INSERT → Datenbank
```

------

# 🧱 **8. Kurzfassung – das Wichtigste in einem Satz**

> **Controller = Außenwelt,
>  Service = Geschäftslogik,
>  Repository = Datenbankzugriff,
>  Entity = Datenmodell/Tabelle.**