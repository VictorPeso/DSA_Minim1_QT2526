package edu.upc.dsa;

import edu.upc.dsa.models.Prestac;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;
import edu.upc.dsa.exceptions.CantDoPrestacException;
import edu.upc.dsa.exceptions.EmptyBookListException;

public class BiblioManagerTest {
    BiblioManager bm;
    final static Logger logger = Logger.getLogger(BiblioManagerTest.class);

    @Before
    public void setUp() {
        this.bm = BiblioManagerImpl.getInstance();
        String[][] booksData1 = {
                {"JV7d", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
                {"JV4a", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
                {"JV1", "Journey to the Center of the Earth", "Dover Publications", "First Edition", "1864", "978-0486268685", "Jules Verne", "Adventures"},
                {"JV3", "Around the World in Eighty Days", "CreateSpace", "First Edition", "1872", "978-1516887907", "Jules Verne", "Adventures"},
                {"JV4c", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
                {"JV8", "The Begum's Fortune", "BiblioBazaar", "First Edition", "1879", "978-1103325575", "Jules Verne", "Adventures"},
                {"JV7c", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
                {"JV5", "The Adventures of Captain Hatteras", "Wordsworth Editions", "First Edition", "1866", "978-1853260257", "Jules Verne", "Adventures"},
                {"JV2b", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
                {"JV2c", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
                // numStack: 0
                {"JV2a", "Twenty Thousand Leagues Under the Sea", "Signet Classics", "First Edition", "1870", "978-0451530960", "Jules Verne", "Adventures"},
                {"JV6", "From the Earth to the Moon", "Oxford University Press", "First Edition", "1865", "978-0199538474", "Jules Verne", "Adventures"},
                {"JV7a", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"},
                {"JV4b", "The Mysterious Island", "Barnes & Noble Classics", "First Edition", "1874", "978-1435149408", "Jules Verne", "Adventures"},
                {"JV7b", "The Steam House", "Forgotten Books", "First Edition", "1880", "978-1605062234", "Jules Verne", "Adventures"}
        };
        for (String[] vec: booksData1){
            this.bm.addLlibre(vec[0], vec[5], vec[1], vec[6], vec[2], vec[4], vec[7], vec[3]);
        }
        this.bm.addLector("111", "Victor", "Peso Keyer", "45794453G", "08/05/2001", "Castelldefels");
    }

    @After
    public void tearDown() {
        // És un Singleton
        this.bm.clear();
    }

    @Test
    public void addLectorTest() {
        logger.info("******************************************");
        logger.info("   ----- AFEGIR UN NOU LECTOR -----");

        Assert.assertEquals(1, bm.size_lectors());

        this.bm.addLector("112", "Marta", "Gonzalez Martinez", "47463829T", "11/11/2001", "Castelldefels");

        Assert.assertEquals(2, bm.size_lectors());
        logger.info("******************************************");
    }

    @Test
    public void addLlibreTest() {
        logger.info("******************************************");
        logger.info("   ----- EMMAGATZEMAR ELS LLIBRES -----");

        Assert.assertEquals(5, bm.size_munt());
        Assert.assertEquals(1, bm.size_biblio());

        // Afegim llibre doomy per probar si funciona.
        this.bm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");

        Assert.assertEquals(6, bm.size_munt());
        Assert.assertEquals(1, bm.size_biblio());

        this.bm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.bm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.bm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.bm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.bm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");

        Assert.assertEquals(1, bm.size_munt());
        Assert.assertEquals(2, bm.size_biblio());

        logger.info("******************************************");
    }

    @Test
    public void saveLlibreTest() throws EmptyBookListException {
        logger.info("******************************************");
        logger.info("   ----- CATALOGAR ELS LLIBRES -----");

        Assert.assertEquals(0, bm.size_catalog());

        Assert.assertThrows(EmptyBookListException.class, () -> this.bm.saveLlibre());

        // Hi han 8 llibres diferents, per tant el cataleg ha de tindre aqeusta dimensió.
        Assert.assertEquals(8, bm.size_catalog());
        // "Twenty Thousand Leagues Under the Sea" ha de tindre 3 exemplars.
        Assert.assertEquals(3, bm.get_catalog().get(0).getExemplars());

        logger.info("******************************************");
    }

    @Test
    public void doPrestecTest() throws Exception {
        logger.info("******************************************");
        logger.info("   ----- PRESTAR UN LLIBRE -----");

        logger.info(" -> Primer enregistrar els llibres:");
        Assert.assertThrows(EmptyBookListException.class, () -> this.bm.saveLlibre());
        Assert.assertEquals(0, bm.size_registre());
        logger.info(("........................................."));

        logger.info(" -> Intent de prestec correcte");
        Assert.assertNotNull("Prestec efectuat correctament", this.bm.doPrestac("", "111", "JV5", "07/11/2025", "07/12/2025"));
        Assert.assertEquals(1, bm.size_registre());
        logger.info(("........................................."));

        logger.info(" -> Intent amb Lector incorrecte");
        Assert.assertThrows(CantDoPrestacException.class, () -> this.bm.doPrestac("0001", "000", "JV2c", "07/11/2025", "07/12/2025"));
        Assert.assertEquals(1, bm.size_registre());
        logger.info(("........................................."));

        logger.info(" -> Intent amb ID Llibre incorrecte");
        Assert.assertThrows(CantDoPrestacException.class, () -> this.bm.doPrestac("0001", "111", "JJJJ", "07/11/2025", "07/12/2025"));
        Assert.assertEquals(1, bm.size_registre());
        logger.info(("........................................."));

        logger.info(" -> Intent amb Llibre exhaurit");
        Assert.assertThrows(CantDoPrestacException.class, () -> this.bm.doPrestac("0001", "111", "JV5", "07/11/2025", "07/12/2025"));
        Assert.assertEquals(1, bm.size_registre());
        logger.info(("........................................."));

        logger.info("******************************************");
    }

    @Test
    public void getLLibresByUserTest() throws Exception{
        logger.info("******************************************");
        logger.info("   ----- CONSULTAR PRESTECS D'UN LECTOR -----");

        logger.info(" -> Set up:");
        this.bm.addLector("112", "Marta", "Gonzalez Martinez", "47463829T", "11/11/2001", "Castelldefels");
        Assert.assertThrows(EmptyBookListException.class, () -> this.bm.saveLlibre());
        Assert.assertNotNull("Prestec efectuat correctament", this.bm.doPrestac("", "111", "JV1", "07/11/2025", "07/12/2025"));
        Assert.assertNotNull("Prestec efectuat correctament", this.bm.doPrestac("", "111", "JV3", "07/11/2025", "07/12/2025"));
        Assert.assertNotNull("Prestec efectuat correctament", this.bm.doPrestac("", "111", "JV5", "07/11/2025", "07/12/2025"));
        Assert.assertNotNull("Prestec efectuat correctament", this.bm.doPrestac("", "112", "JV8", "07/11/2025", "07/12/2025"));
        logger.info(("........................................."));

        logger.info(" -> Llibres de l'usuari 111:");
        Assert.assertEquals(3, bm.getLLibresByUser(this.bm.get_lectors().get(0)).size());
        logger.info(("........................................."));

        logger.info(" -> Llibres de l'usuari 112:");
        Assert.assertEquals(1, bm.getLLibresByUser(this.bm.get_lectors().get(1)).size());
        logger.info(("........................................."));

        logger.info("******************************************");
    }
}
