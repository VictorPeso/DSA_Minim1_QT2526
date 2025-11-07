package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;

public class Lector {

    String id;
    String nom;
    String cognoms;
    String dni;
    String birthdate;
    String direccio;

    public Lector() {
        this.setId(RandomUtils.getId());
    }

    public Lector(String id, String nom, String cognoms, String dni, String birthdate, String direccio) {
        this();
        if (id != null) this.setId(id);
        this.setNom(nom);
        this.setCognoms(cognoms);
        this.setDni(dni);
        this.setBirthdate(birthdate);
        this.setDireccio(direccio);

    }

    @Override
    public String toString() {
        return "Lector [ID = " + id +", Nom = " + nom +", Cognoms = " + cognoms +", DNI = " + dni +", Data de neixement = " + birthdate +", Adreça = " + direccio + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }
}
