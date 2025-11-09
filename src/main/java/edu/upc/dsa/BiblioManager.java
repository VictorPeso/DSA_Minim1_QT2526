package edu.upc.dsa;

import edu.upc.dsa.exceptions.CantDoPrestacException;
import edu.upc.dsa.exceptions.EmptyBookListException;
import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Llibre;
import edu.upc.dsa.models.Prestac;

import java.util.List;
import java.util.Stack;

public interface BiblioManager {

    public Lector addLector(String id, String nom, String cognoms, String dni, String birthdate, String direccio);

    public Llibre addLlibre(String id, String isbn, String title, String autor, String editorial, String any_publicacio, String tematica, String num_edicio);

    public void saveLlibre() throws EmptyBookListException;

    public Prestac doPrestac(String id, String lec, String llibre, String data_prestac, String data_devolucio) throws CantDoPrestacException;

    public List<Prestac> getLLibresByUser(Lector lec);

//    public Llibre addTrack(String id, String title, String singer);
//    public Llibre addTrack(String title, String singer);
//    public Llibre addTrack(Llibre t);
//    public Llibre getTrack(String id);
//    public Llibre getTrack2(String id) throws EmptyBookListException;
//
//    public List<Llibre> findAll();
//    public void deleteTrack(String id);
//    public Llibre updateTrack(Llibre t);
//
    public void clear();
    public int size_munt();
    public int size_biblio();
    public List<Llibre> get_catalog();
    public int size_catalog();
    public int size_registre();
    public List<Lector> get_lectors();
    public int size_lectors();
}
