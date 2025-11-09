package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Prestac {

    String id;
    String id_lector;
    String id_llibre;
    String data_prestac;
    String data_devolucio;
    String estat;

    public Prestac() {
        this.setId(RandomUtils.getId());
    }

    public Prestac(String id, String id_lector, String id_llibre, String data_prestac, String data_devolucio) {
        this();
        if (id != null) this.setId(id);
        this.setId_lector(id_lector);
        this.setId_llibre(id_llibre);
        this.setData_prestac(data_prestac);
        this.setData_devolucio(data_devolucio);
        this.setEstat("En tràmit");
    }

    @Override
    public String toString() {
        return "Prestec [ID = " + id + ", Lector (ID) = " + id_lector + ", Llibre (ID) = " + id_llibre + ", Data del prestec = " + data_prestac + ", Data devolució = " + data_devolucio + ", Estat = " + estat + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_lector() {
        return id_lector;
    }

    public void setId_lector(String id_lector) {
        this.id_lector = id_lector;
    }

    public String getId_llibre() {
        return id_llibre;
    }

    public void setId_llibre(String id_llibre) {
        this.id_llibre = id_llibre;
    }

    public String getData_prestac() {
        return data_prestac;
    }

    public void setData_prestac(String data_prestac) {
        this.data_prestac = data_prestac;
    }

    public String getData_devolucio() {
        return data_devolucio;
    }

    public void setData_devolucio(String data_devolucio) {
        this.data_devolucio = data_devolucio;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
}
