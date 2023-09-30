package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @BeforeEach
    public void setUp() {
        card1 = new Card('R', 5);
        card2 = new Card('Y', 2);
        card3 = new Card('B', 9);
        card4 = new Card('G', 0);
    }

    @Test
    @DisplayName("Sjekker at konstruktøren utløser unntak dersom input er ugyldig")
    public void testConstructor() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Card('X', 1);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Card('R', 10);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Card('Y', -1);
        });
    }

    @Test
    @DisplayName("Sjekker at korrekt tall returneres")
    public void testGetNumber() {
        Assertions.assertEquals(5, card1.getNumber());
        Assertions.assertEquals(2, card2.getNumber());
        Assertions.assertEquals(9, card3.getNumber());
        Assertions.assertEquals(0, card4.getNumber());
    }

    @Test
    @DisplayName("Sjekker at korrekt farge returneres")
    public void testGetColor() {
        Assertions.assertEquals('R', card1.getColor());
        Assertions.assertEquals('Y', card2.getColor());
        Assertions.assertEquals('B', card3.getColor());
        Assertions.assertEquals('G', card4.getColor());
    }

    @Test
    @DisplayName("Tester toString metoden")
    public void testToString() {
        Assertions.assertEquals("R5", new Card('R', 5).toString());;
        Assertions.assertEquals("Y2", new Card('Y', 2).toString());;
        Assertions.assertEquals("B9", new Card('B', 9).toString());;
        Assertions.assertEquals("G0", new Card('G', 0).toString());;
    }
}
