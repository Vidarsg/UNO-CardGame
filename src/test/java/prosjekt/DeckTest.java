package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DeckTest {
    
    private Deck deck1;
    private Deck deck2;
    private Deck deck3;

    @BeforeEach
    public void setUp() {
        deck1 = new Deck(9);
        deck2 = new Deck(4);
        deck3 = new Deck(0);
    }

    @Test
    @DisplayName("Sjekker at konstruktøren utløser unntak dersom input er ugyldig")
    public void testConstructor() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Deck(10);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Deck(-1);
        });
    }

    @Test
    @DisplayName("Sjekker at korrekt størrelse på kortstokk returneres")
    public void testGetDeckSize() {
        Assertions.assertEquals(80, deck1.getDeckSize());
        Assertions.assertEquals(40, deck2.getDeckSize());
        Assertions.assertEquals(8, deck3.getDeckSize());
    }

    @Test
    @DisplayName("Sjekker om kortstokken er tom")
    public void testIsDeckEmpty() {
        Assertions.assertEquals(false, deck3.isDeckEmpty());
        
        for (int i = 0; i < 8; i++) {
            deck3.drawFromDeck();
        }

        Assertions.assertEquals(true, deck3.isDeckEmpty());

    }

    @Test
    @DisplayName("Sjekker at korrekt antall kort blir trukket fra kortstokken")
    public void testDrawFromDeck() {
        Assertions.assertEquals(80, deck1.getDeckSize());
        deck1.drawFromDeck();
        deck1.drawFromDeck();
        Assertions.assertEquals(78, deck1.getDeckSize());
        while (!deck1.isDeckEmpty()) {
            deck1.drawFromDeck();
        }
        Assertions.assertThrows(IllegalStateException.class, () -> {
            deck1.drawFromDeck();
        });
    }

}
