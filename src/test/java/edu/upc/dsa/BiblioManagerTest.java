package edu.upc.dsa;

import edu.upc.dsa.exceptions.CantDoPrestacException;
import edu.upc.dsa.exceptions.EmptyBookListException;
import edu.upc.dsa.models.Llibre;
import edu.upc.dsa.models.Prestac;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BiblioManagerTest {
    BiblioManager tm;

    @Before
    public void setUp() {
        this.tm = BiblioManagerImpl.getInstance();
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
            this.tm.addLlibre(vec[5], vec[0], vec[1], vec[6], vec[2], vec[4], vec[7], vec[3]);
        }
        this.tm.addLector("111", "Victor", "Peso Keyer", "45794453G", "08/05/2001", "Castelldefels");
    }

    @After
    public void tearDown() {
        // És un Singleton
        this.tm.clear();
    }

    @Test
    public void addLlibreTest() {
        Assert.assertEquals(5, tm.size());

        this.tm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");

        Assert.assertEquals(6, tm.size());

        this.tm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.tm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.tm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.tm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");
        this.tm.addLlibre("a", "a", "a", "a", "a", "a", "a", "a");

        Assert.assertEquals(1, tm.size());
    }

    @Test
    public void saveLlibreTest() {
        Assert.assertEquals(0, tm.size2());

        Assert.assertThrows(EmptyBookListException.class, () -> this.tm.saveLlibre());

        Assert.assertEquals(8, tm.size2());
    }

    @Test
    public void doPrestecTest() {
        Assert.assertEquals(0, tm.size3());
        Assert.assertThrows(CantDoPrestacException.class, () -> tm.doPrestac("1", "b", "a", "b", "b"));
        Assert.assertThrows(CantDoPrestacException.class, () -> tm.doPrestac("1", "a", "a", "b", "b"));
        Assert.assertEquals(1, tm.size3());
    }
}
