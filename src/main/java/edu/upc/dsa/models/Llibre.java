package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Llibre {

    String id;
    String isbn;
    String title;
    String autor;
    String editorial;
    String any_publicacio;
    String tematica;
    String num_edicio;
    int exemplars;

    public Llibre() {
        this.setId(RandomUtils.getId());
        this.exemplars = 1;
    }

    public Llibre(String id, String isbn, String title, String autor, String editorial, String any_publicacio, String tematica, String num_edicio) {
        this();
        if (id != null) this.setId(id);
        this.setIsbn(isbn);
        this.setAutor(autor);
        this.setTitle(title);
        this.setEditorial(editorial);
        this.setAny_publicacio(any_publicacio);
        this.setTematica(tematica);
        this.setNum_edicio(num_edicio);
    }

    @Override
    public String toString() {
        return "Llibre [ID = "+id+", Titol = " + title + ", Autor = " + autor +", ISBN = " + isbn + ", Editorial = " + editorial + ", Any Publicacio = " + any_publicacio + ", Tematica = " + tematica + ", Numero d'edicio = " + num_edicio + ", Exemplars = " + exemplars + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getExemplars() {
        return exemplars;
    }

    public void setExemplars(int exemplars) {
        this.exemplars = exemplars;
    }

    public String getNum_edicio() {
        return num_edicio;
    }

    public void setNum_edicio(String num_edicio) {
        this.num_edicio = num_edicio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getAny_publicacio() {
        return any_publicacio;
    }

    public void setAny_publicacio(String any_publicacio) {
        this.any_publicacio = any_publicacio;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}