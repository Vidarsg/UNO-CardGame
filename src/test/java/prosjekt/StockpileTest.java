package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StockpileTest {
    
    private Stockpile pile;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @BeforeEach
    public void setUp() {
        pile= new Stockpile();
        card1 = new Card('R', 2);
        card2 = new Card('Y', 6);
        card3 = new Card('B', 1);
        card4 = new Card('G', 0);
    }

    @Test
    @DisplayName("Sjekker at kort blir lagt til i bunken")
    public void testAddToPile() {
        pile.addToPile(card1);
        pile.addToPile(card2);
        Assertions.assertEquals(2, pile.getPileSize());
    }

    @Test
    @DisplayName("Sjekker at øverste kort i bunken returneres")
    public void testGetTopCard() {
        pile.addToPile(card1);
        pile.addToPile(card2);
        pile.addToPile(card3);
        pile.addToPile(card4);
        Assertions.assertEquals(card4, pile.getTopCard());
    }

    @Test
    @DisplayName("Sjekker om bunken er tom")
    public void testIsPileEmpty() {
        Assertions.assertEquals(true, pile.isPileEmpty());
        pile.addToPile(card1);
        Assertions.assertEquals(false, pile.isPileEmpty());
    }
}
