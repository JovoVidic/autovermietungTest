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

| Task                        | Status / Kommentar                                                                                                                                                                                                                                          |
| --------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Entities erstellen          | ✅ Auto.java, Customer.java, Booking.java existieren (`@Entity` vorhanden)                                                                                                                                                                                   |
| Repositories erstellen      | ✅ AutoRepository.java, CustomerRepository.java, BookingRepository.java existieren                                                                                                                                                                           |
| Services erstellen          | ✅ AutoService.java, CustomerService.java, BookingService.java existieren <br/>✅ Logging in allen Services implementiert (`APP_LOG`, `ERROR_LOG`, `DATABASE_LOG`, `BOOKING_LOG`)                                                                             |
| REST Controller erstellen   | ✅ AutoController.java, CustomerController.java, BookingController.java existieren <br/>✅ Endpunkte `/api/autos`, `/api/customers`, `/api/bookings` funktionsfähig <br/>✅ Logging in allen Controllern implementiert (`APP_LOG`, `ERROR_LOG`, `BOOKING_LOG`) |
| Logging einrichten          | ✅ Logging für CRUD-Operationen und Business-Events implementiert                                                                                                                                                                                            |
| Unit-Tests schreiben        | ✅ AutoServiceTest & AutoRepositoryTest existieren <br/>✅ CustomerControllerTest + BookingControllerTest existieren und decken GET/POST/PUT/DELETE ab <br/>⚠️ Service-Tests für CustomerService & BookingService noch teilweise pendent                      |
| Swagger / API-Dokumentation | ✅ Swagger UI erreichbar unter `/swagger-ui.html` <br/>✅ Endpunkte dokumentiert, kann für Testing & API-Referenz genutzt werden                                                                                                                              |


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

