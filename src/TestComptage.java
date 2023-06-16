import org.junit.*;
import org.junit.runner.*;

import modele.entities.*;

import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

import java.sql.Date;
import java.util.Arrays;

@FixMethodOrder(MethodSorters.JVM)
public class TestComptage {
    private Comptage comptage;

    @Before
    public void instancier() {
        int[] passages = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
        Quartier quartier = new Quartier(1, "Quartier 1", 100);
        Compteur compteur = new Compteur(1, "Compteur 1", "Sens 1", 1.0f, 1.0f, quartier);
        DateInfo dateInfo = new DateInfo(new Date(1609455600000L), 10, Jour.Lundi, Vacances.Nulle);
        this.comptage = new Comptage(passages, PresenceAnomalie.Nulle, compteur, dateInfo);
    }

    @Test
    public void testGetPassage() {
        System.out.println("testGetPassage");
        assertEquals(5, comptage.getPassage(4));
        System.out.println("Test réussi : " + comptage.getPassage(4));
    }

    @Test
    public void testSetPassage() {
        System.out.println("testSetPassage");
        comptage.setPassage(4, 10);
        assertEquals(10, comptage.getPassage(4));
        System.out.println("Test réussi : " + comptage.getPassage(4));
    }

    @Test
    public void testGetPassages() {
        System.out.println("testGetPassages");
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24}, comptage.getPassages());
        System.out.println("Test réussi : " + Arrays.toString(comptage.getPassages()));
    }

    @Test
    public void testSetPassages() {
        System.out.println("testSetPassages");
        int[] newPassages = new int[]{24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        comptage.setPassages(newPassages);
        assertArrayEquals(newPassages, comptage.getPassages());
        System.out.println("Test réussi : " + Arrays.toString(comptage.getPassages()));
    }

    @Test
    public void testGetAnomalie() {
        System.out.println("testGetAnomalie");
        assertEquals(PresenceAnomalie.Nulle, comptage.getAnomalie());
        System.out.println("Test réussi : " + comptage.getAnomalie());
    }

    @Test
    public void testSetAnomalie() {
        System.out.println("testSetAnomalie");
        comptage.setAnomalie(PresenceAnomalie.Forte);
        assertEquals(PresenceAnomalie.Forte, comptage.getAnomalie());
        System.out.println("Test réussi : " + comptage.getAnomalie());
    }

    @Test
    public void testGetLeCompteur() {
        System.out.println("testGetLeCompteur");
        Compteur compteur = comptage.getLeCompteur();
        assertEquals("Compteur 1", compteur.getNomCompteur());
        System.out.println("Test réussi : " + compteur.getNomCompteur());
    }

    @Test
    public void testSetLeCompteur() {
        System.out.println("testSetLeCompteur");
        Quartier quartier = new Quartier(2, "Quartier 2", 200);
        Compteur compteur = new Compteur(2, "Compteur 2", "Sens 2", 2.0f, 2.0f, quartier);
        comptage.setLeCompteur(compteur);
        Compteur newCompteur = comptage.getLeCompteur();
        assertEquals("Compteur 2", newCompteur.getNomCompteur());
        System.out.println("Test réussi : " + newCompteur.getNomCompteur());
    }

    @Test
    public void testGetLaDate() {
        System.out.println("testGetLaDate");
        DateInfo laDate = comptage.getLaDate();
        assertEquals(new Date(1609455600000L), laDate.getDate());
        System.out.println("Test réussi : " + laDate.getDate());
    }

    @Test
    public void testSetLaDate() {
        System.out.println("testSetLaDate");
        DateInfo dateInfo = new DateInfo(new Date(1612047600000L), 20, Jour.Mardi, Vacances.Noel);
        comptage.setLaDate(dateInfo);
        assertEquals(new Date(1612047600000L), comptage.getLaDate().getDate());
        System.out.println("Test réussi : " + comptage.getLaDate().getDate());
    }

    @Test
    public void testToString() {
        System.out.println("testToString");
        String expected = "Comptage(laDate : 2021-01-01, leCompteur : 1, anomalie : Nulle, passages : [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24])";
        assertEquals(expected, comptage.toString());
        System.out.println("Test réussi : " + comptage.toString());
    }

    @Test
    public void testTotalPassages() {
        System.out.println("testTotalPassages");
        assertEquals(300, comptage.totalPassages());
        System.out.println("Test réussi : " + comptage.totalPassages());
    }

    @Test
    public void testAveragePassages() {
        System.out.println("testAveragePassages");
        assertEquals(12.5f, comptage.averagePassages(), 0.001);
        System.out.println("Test réussi : " + comptage.averagePassages());
    }

    public static void main (String args[]) {
        JUnitCore.main("TestComptage");
    }
}
