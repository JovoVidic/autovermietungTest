package ch.juventus.autovermietung.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Auto auto;

    @ManyToOne
    private Customer customer;

    private LocalDate startDatum;
    private LocalDate endDatum;
    private double gesamtPreis;

    public Booking() {}

    public Booking(Long id, Auto auto, Customer customer, LocalDate startDatum, LocalDate endDatum, double gesamtPreis) {
        this.id = id;
        this.auto = auto;
        this.customer = customer;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.gesamtPreis = gesamtPreis;
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Auto getAuto() { return auto; }
    public void setAuto(Auto auto) { this.auto = auto; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public LocalDate getStartDatum() { return startDatum; }
    public void setStartDatum(LocalDate startDatum) { this.startDatum = startDatum; }
    public LocalDate getEndDatum() { return endDatum; }
    public void setEndDatum(LocalDate endDatum) { this.endDatum = endDatum; }
    public double getGesamtPreis() { return gesamtPreis; }
    public void setGesamtPreis(double gesamtPreis) { this.gesamtPreis = gesamtPreis; }
}
