package edu.upc.dsa;

import edu.upc.dsa.exceptions.CantDoPrestacException;
import edu.upc.dsa.exceptions.EmptyBookListException;
import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Llibre;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import edu.upc.dsa.models.Prestac;
import org.apache.log4j.Logger;

public class BiblioManagerImpl implements BiblioManager {
    private static BiblioManager instance;
    protected Stack<Llibre> munt;
    protected List<Stack<Llibre>> biblio;
    protected List<Llibre> catalog;
    protected List<Lector> lectors;
    protected List<Prestac> registre;
    final static Logger logger = Logger.getLogger(BiblioManagerImpl.class);


    private BiblioManagerImpl() {
        this.biblio = new LinkedList<>();
        this.lectors = new LinkedList<>();
        this.munt = new Stack<>();
        this.catalog = new LinkedList<>();
        this.registre = new LinkedList<>();
    }

    public static BiblioManager getInstance() {
        if (instance==null) instance = new BiblioManagerImpl();
        return instance;
    }

    public Lector addLector(String id, String nom, String cognoms, String dni, String birthdate, String direccio) {
        Lector l = new Lector(id, nom, cognoms, dni, birthdate, direccio);
        this.lectors.add(l);
        logger.info("LECTOR NOU: " + l);
        return l;
    }

    public Llibre addLlibre(String id, String isbn, String title, String autor, String editorial, String any_publicacio, String tematica, String num_edicio) {
        Stack<Llibre> munt_complert;
        Llibre l = new Llibre(id, isbn, title, autor, editorial, any_publicacio, tematica, num_edicio);
        if (munt.size() == 9) {
            this.munt.push(l);
            munt_complert = (Stack<Llibre>) this.munt.clone();
            this.biblio.add(munt_complert);
            this.munt.clear();
            logger.info("LLIBRE NOU: " + l);
            logger.info(" ------- S'enmagatzema el munt -------");
        }
        else {
            this.munt.push(l);
            logger.info("LLIBRE NOU: " + l);
        }
        return l;
    }

    private void catalogar_munt(Stack<Llibre> m) {
        boolean found = false;
        while (!m.isEmpty()) {
            Llibre llibre = m.peek();
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
            m.pop();
        }
    }

    public void saveLlibre() throws EmptyBookListException {
        if ((this.munt.isEmpty()) && (this.biblio.isEmpty())) {
            throw new EmptyBookListException("No hi ha llibres per enregistrar.");
        }
        else {
            for (Stack<Llibre> m : this.biblio) {
                catalogar_munt(m);
            }
            catalogar_munt(this.munt);
            logger.info("S'ha acabat d'enrregistrar els llibres");
        }
    }

    public Prestac doPrestac(String id, String lector, String llibre, String data_prestac, String data_devolucio) throws CantDoPrestacException {
        Prestac p = new Prestac(id, lector, llibre, data_prestac, data_devolucio);
        boolean found_lec = false;
        boolean found_llibre_available = false;

        int lec_count = 0;
        int llib_count = 0;

        while (!found_lec && lec_count < this.lectors.size()) {
            if (this.lectors.get(lec_count).getId().equals(lector)) {
                found_lec = true;

                while (!found_llibre_available && llib_count < this.catalog.size()) {
                    if (this.catalog.get(llib_count).getId().equals(llibre)) {
                        if (this.catalog.get(llib_count).getExemplars() >= 1) {
                            found_llibre_available = true;
                            int num = this.catalog.get(llib_count).getExemplars() - 1;
                            this.catalog.get(llib_count).setExemplars(num);
                            this.registre.add(p);
                            logger.info("PRESTAC NOU: " + p);
                            return p;
                        }
                    }
                    llib_count = llib_count + 1;
                }
            }
            lec_count = lec_count + 1;
        }

        if (!found_lec) {
            logger.info("ERROR: Lector no enregistrat.");
            p = null;
            throw new CantDoPrestacException("Lector no enregistrat.");
        }
        if (!found_llibre_available) {
            logger.info("ERROR: Llibre no disponible.");
            p = null;
            throw new CantDoPrestacException("Llibre no disponible.");
        }
        return p;
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

    public int size_munt() {
        int ret = this.munt.size();
        logger.info("Mida del munt actual: " + ret);
        return ret;
    }

    public int size_biblio() {
        int ret = this.biblio.size();
        logger.info("Munts complerts: " + ret);
        return ret;
    }

    public int size_catalog() {
        int ret = this.catalog.size();
        logger.info("Mida del cataleg: " + ret);
        return ret;
    }

    public int size_registre() {
        int ret = this.registre.size();
        logger.info("Mida del registre: " + ret);
        return ret;
    }

    public int size_lectors() {
        int ret = this.lectors.size();
        logger.info("Nombre de lectors: " + ret);
        return ret;
    }

    public List<Llibre> get_catalog() {
        return this.catalog;
    }

    public List<Lector> get_lectors() {
        return this.lectors;
    }

    public Stack<Llibre> get_munt() {
        return this.munt;
    }

    public List<Stack<Llibre>> get_biblio() {
        return this.biblio;
    }

    public List<Prestac> get_registre() {
        return this.registre;
    }
}