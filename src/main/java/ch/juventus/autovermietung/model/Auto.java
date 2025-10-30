package ch.juventus.autovermietung.model;

import jakarta.persistence.*;

@Entity
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marke;
    private String modell;
    private String kennzeichen;
    private boolean verfuegbar;
    private double preisProTag;

    public Auto() {
    }

    public Auto(String marke, String modell, String kennzeichen, boolean verfuegbar, double preisProTag) {
        this.marke = marke;
        this.modell = modell;
        this.kennzeichen = kennzeichen;
        this.verfuegbar = verfuegbar;
        this.preisProTag = preisProTag;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    public double getPreisProTag() {
        return preisProTag;
    }

    public void setPreisProTag(double preisProTag) {
        this.preisProTag = preisProTag;
    }
}