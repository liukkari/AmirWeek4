package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ExtraTest extends AbstractParent {

    private static Laskin laskin = new Laskin();
    private final double DELTA = 0.001;

    @BeforeAll
    public static void testVirtaON() {
        System.out.println("@BeforeAll Virta ON (ennen ensimmäistä testiä)");
        laskin.virtaON();
    }

    @AfterAll
    public static void testVirtaOFF() {
        System.out.println("@AfterAll Virta OFF (kaikki testit ajettu).");
        laskin.virtaOFF();
        laskin = null;
    }

    @BeforeEach
    public void testNollaa() {
        System.out.println("  Nollaa laskin.");
        laskin.nollaa();
        assertEquals(0, laskin.annaTulos(), DELTA, "Nollaus ei onnistunut");
    }

    @ParameterizedTest(name="Luvun {0} neliö on {1}")
    @CsvSource({ "2, 4", "4, 16", "5, 25" })
    public void testNelio(double luku, double tulos) {
        laskin.nelio(luku);
        assertEquals(tulos, laskin.annaTulos(), DELTA, "Neliöön korotus väärin");
    }

    @Test
    public void testNeliojuuri2() {
        laskin.neliojuuri(2);
        assertEquals(Math.sqrt(2), laskin.annaTulos(), DELTA, "Luvun 2 neliöjuuri väärin");
    }

    @Test
    @DisplayName("Testaa negatiivinen neliöjuuri")
    public void testNeliojuuriNegat() {
        ArithmeticException poikkeus = assertThrows(ArithmeticException.class, () -> laskin.neliojuuri(-1));
        assertEquals("Negatiivisesta luvusta ei voi ottaa neliöjuurta", poikkeus.getMessage());
    }

    @ParameterizedTest(name="Luvun {0} tulos on {1}")
    @CsvSource({ "2, 4", "3, 9", "4, 16" })
    public void testProduct(double a, double b) {
        laskin.product(a, b);
        assertEquals(a * b, laskin.annaTulos(), DELTA, "Kertolasku väärin");
    }

    @ParameterizedTest(name="Luvun {0} neliöjuuri on {1}")
    @CsvSource({ "4, 2", "9, 3", "16, 4" })
    public void testSquareroot(double a, double b) {
        laskin.squareroot(a);
        assertEquals(b, laskin.annaTulos(), DELTA, "Neliöjuuri väärin");
    }
}