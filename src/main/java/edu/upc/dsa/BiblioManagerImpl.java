package edu.upc.dsa;

import edu.upc.dsa.exceptions.CantDoPrestacException;
import edu.upc.dsa.exceptions.EmptyBookListException;
import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Llibre;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.upc.dsa.models.Prestac;
import org.apache.log4j.Logger;

public class BiblioManagerImpl implements BiblioManager {
    private static BiblioManager instance;
    protected Queue<Llibre> munt;
    protected List<Queue<Llibre>> biblio;
    protected List<Llibre> catalog;
    protected List<Lector> lectors;
    protected List<Prestac> registre;
    final static Logger logger = Logger.getLogger(BiblioManagerImpl.class);


    private BiblioManagerImpl() {
        this.biblio = new LinkedList<>();
        this.lectors = new LinkedList<>();
        this.munt = new LinkedList<>();
        this.catalog = new LinkedList<>();
        this.registre = new LinkedList<>();
    }

    public static BiblioManager getInstance() {
        if (instance==null) instance = new BiblioManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.munt.size();
        logger.info("size " + ret);

        return ret;
    }

    public int size2() {
        int ret = this.catalog.size();
        logger.info("size catalog " + ret);

        return ret;
    }
    public int size3() {
        int ret = this.registre.size();
        logger.info("size catalog " + ret);

        return ret;
    }


    public Lector addLector(String id, String nom, String cognoms, String dni, String birthdate, String direccio){
        Lector l = new Lector(id, nom, cognoms, dni, birthdate, direccio);
        this.lectors.add(l);
        logger.info("LECTOR NOU: " + l);
        return l;
    }

    public Llibre addLlibre(String id, String isbn, String title, String autor, String editorial, String any_publicacio, String tematica, String num_edicio) {
        Llibre l = new Llibre(id, isbn, title, autor, editorial, any_publicacio, tematica, num_edicio);
        if (munt.size() == 9) {
            this.munt.add(l);
            this.biblio.add(munt);
            this.munt.clear();
            logger.info("LLIBRE NOU: " + l);
            logger.info(" ------- S'enmagatzema el munt -------");
        }
        else {
            this.munt.add(l);
            logger.info("LLIBRE NOU: " + l);
        }
        return l;
    }

    public void saveLlibre() throws EmptyBookListException {
        boolean found = false;
        for (Queue<Llibre> m : this.biblio) {
            while (!m.isEmpty()) {
                Llibre llibre = m.poll();
                found = false;
                for (Llibre l : this.catalog) {
                    if (l.getIsbn().equals(llibre.getIsbn())) {
                        int n = l.getExemplars() + 1;
                        l.setExemplars(n);
                        logger.info("Augment a " + n + " exemplars del Llibre: " + llibre);
                        found = true;
                    }
                }
                if (!found) {
                    this.catalog.add(llibre);
                    logger.info("LLIBRE ENRREGISTRAT: " + llibre);
                }
            }
        }
        logger.info("S'ha acabat d'enrregistrar els llibres");
        throw new EmptyBookListException("Fi de l'enrregistrament");
    }

    public Prestac doPrestac(String id, String lector, String llibre, String data_prestac, String data_devolucio) throws CantDoPrestacException {
        Prestac p = new Prestac(id, lector, llibre, data_prestac, data_devolucio);
        boolean found_lec = false;
        boolean found_llibre_available = false;
        for (Lector l : this.lectors) {
            if (l.getId().equals(lector)) {
                found_lec = true;
            }
        }
        for (Llibre l : this.catalog) {
            if (l.getId().equals(llibre)) {
                if (l.getExemplars() >= 1) {
                    found_llibre_available = true;
                }
            }
        }
        if (found_lec && found_llibre_available) {
            this.registre.add(p);
            logger.info("PRESTAC NOU: " + p);
            return p;
        }
        else {
            if (!found_lec) {
                logger.info("ERROR: Lector no enregistrat.");
                throw new CantDoPrestacException("Lector no enregistrat.");
            }
            if (!found_llibre_available) {
                logger.info("ERROR: Llibre no disponible.");
                throw new CantDoPrestacException("Llibre no disponible.");
            }
            return null;
        }
    }

    public List<Prestac> getLLibresByUser(Lector lec) {
        List<Prestac> llista = new LinkedList<>();
        for (Prestac p : this.registre) {
            if (p.getId_lector().equals(lec.getId())) {
                llista.add(p);
            }
        }
        logger.info("S'ha obtingut la llista de prestecs del Lector: " + lec);
        return llista;
    }

    public void clear() {
        this.munt.clear();
        this.biblio.clear();
        this.catalog.clear();
        this.lectors.clear();
        this.registre.clear();
    }

//    public Llibre addTrack(Llibre t) {
//        logger.info("new Track " + t);
//
//        this.munt.add (t);
//        logger.info("new Track added");
//        return t;
//    }
//
//    public Llibre addTrack(String title, String singer){
//        return this.addTrack(null, title, singer);
//    }
//
//
//    public Llibre getTrack(String id) {
//        logger.info("getTrack("+id+")");
//
//        for (Llibre t: this.munt) {
//            if (t.getId().equals(id)) {
//                logger.info("getTrack("+id+"): "+t);
//
//                return t;
//            }
//        }
//
//        logger.warn("not found " + id);
//        return null;
//    }
//
//    public Llibre getTrack2(String id) throws EmptyBookListException {
//        Llibre t = getTrack(id);
//        if (t == null) throw new EmptyBookListException();
//        return t;
//    }
//
//
//    public List<Llibre> findAll() {
//        return this.munt;
//    }
//
//    @Override
//    public void deleteTrack(String id) {
//
//        Llibre t = this.getTrack(id);
//        if (t==null) {
//            logger.warn("not found " + t);
//        }
//        else logger.info(t+" deleted ");
//
//        this.munt.remove(t);
//
//    }
//
//    @Override
//    public Llibre updateTrack(Llibre p) {
//        Llibre t = this.getTrack(p.getId());
//
//        if (t!=null) {
//            logger.info(p+" rebut!!!! ");
//
//            t.setAutor(p.getAutor());
//            t.setTitle(p.getTitle());
//
//            logger.info(t+" updated ");
//        }
//        else {
//            logger.warn("not found "+p);
//        }
//
//        return t;
//    }
//
//    public void clear() {
//        this.munt.clear();
//    }
}