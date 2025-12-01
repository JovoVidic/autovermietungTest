# 🚗 Projekt Roadmap – Car Booking System

**Schritt-für-Schritt

## 🛠️ Phase 0 – Vorbereitung (Setup)

| Task                        | Status / Kommentar                  |
| --------------------------- | ----------------------------------- |
| GitHub Repository erstellen | ✅ Existiert, aktuelle Version lokal |
| Eclipse Projekt erstellen   | ✅ Backend-Projekt erstellt          |
| VS Code Projekt erstellen   | ✅ Erledigt!                         |
| PostgreSQL DB anlegen       | ✅ DB existiert                      |

------

## 🔧 Phase 1 – Backend: Grundgerüst

| Task                      | Status / Kommentar                                           |
| ------------------------- | ------------------------------------------------------------ |
| Entities erstellen        | ✅ Auto.java existiert (`@Entity` vorhanden)                  |
| Repositories erstellen    | ✅ AutoRepository.java existiert  <br/> ⚠️ CustomerRepository & BookingRepository pendent |
| Services erstellen        | ✅ AutoService.java existiert  <br/>⚠️ CustomerService & BookingService pendent |
| REST Controller erstellen | ✅ AutoController.java existiert (`/api/autos` funktioniert)  <br/>⚠️ CustomerController & BookingController pendent |
| Logging einrichten        | ⚠️ Noch nicht eingerichtet                                    |
| Unit-Tests schreiben      | ✅ AutoServiceTest & AutoRepositoryTest existieren  <br/>⚠️ Customer/Booking Tests pendent |

## 💻 Phase 2 – Frontend: Grundgerüst

| Task                        | Status / Kommentar                                         |
| --------------------------- | ---------------------------------------------------------- |
| Basis-Komponenten erstellen | ⚠️ Noch pendent (CarList, BookingForm, BookingConfirmation) |
| Routing einrichten          | ⚠️ Noch pendent (`/cars`, `/bookings`, `/booking/:id`)      |
| Mock-API erstellen          | ⚠️ Noch pendent                                             |
| Formulare validieren        | ⚠️ Noch pendent                                             |
| Styling starten             | ⚠️ Noch pendent                                             |

------

## 🔗 Phase 3 – Integration Backend + Frontend

| Task                          | Status / Kommentar |
| ----------------------------- | ------------------ |
| API Requests senden           | ⚠️ Noch pendent     |
| JSON Responses verarbeiten    | ⚠️ Noch pendent     |
| Fehlerhandling implementieren | ⚠️ Noch pendent     |
| Logging überprüfen            | ⚠️ Noch pendent     |

------

## 🚀 Phase 4 – Erweiterungen

| Task                  | Status / Kommentar                                       |
| --------------------- | -------------------------------------------------------- |
| Admin-Funktionen      | ⚠️ Noch pendent (Fahrzeuge anlegen, Buchungen stornieren) |
| Filter & Suche        | ⚠️ Noch pendent                                           |
| Preislogik verfeinern | ⚠️ Noch pendent                                           |
| Swagger / OpenAPI     | ⚠️ Noch pendent                                           |

## 🧪 Phase 5 – Tests & Logging erweitern

| Task                 | Status / Kommentar                                           |
| -------------------- | ------------------------------------------------------------ |
| Unit-Tests erweitern | ⚠️ Noch pendent (Preisberechnung, Validierung für alle Entities) |
| Integrationstests    | ⚠️ Noch pendent (Controller + Repository)                     |
| Logging optimieren   | ⚠️ Noch pendent                                               |

------

## 🎉 Phase 6 – Finalisierung

| Task                         | Status / Kommentar |
| ---------------------------- | ------------------ |
| Frontend Styling abschließen | ⚠️ Noch pendent     |
| README + Setup-Guide         | ⚠️ Noch pendent     |
| GitHub final push            | ⚠️ Noch pendent     |
| Präsentationsmaterial        | ⚠️ Noch pendent     |

