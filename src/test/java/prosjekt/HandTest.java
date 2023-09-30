package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    
    private Hand hand;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @BeforeEach
    public void setUp() {
        hand = new Hand();
        card1 = new Card('R', 2);
        card2 = new Card('Y', 6);
        card3 = new Card('B', 1);
        card4 = new Card('G', 0);
    }

    @Test
    @DisplayName("Sjekker at kort blir lagt til å hånden")
    public void testDrawToHand() {
        hand.drawToHand(card1);
        hand.drawToHand(card2);
        Assertions.assertEquals(2, hand.getHandSize());
    }

    @Test
    @DisplayName("Sjekker at kort blir fjernet fra hånden")
    public void testDrawFromHand() {
        hand.drawToHand(card1);
        hand.drawToHand(card2);
        hand.drawToHand(card3);
        hand.drawToHand(card4);
        hand.drawFromHand(3);
        hand.drawFromHand(2);
        Assertions.assertEquals(2, hand.getHandSize());
    }

    @Test
    @DisplayName("Sjekker at korrekt indeks i hånden returneres")
    public void testGetCard() {
        hand.drawToHand(card1);
        hand.drawToHand(card2);
        hand.drawToHand(card3);
        hand.drawToHand(card4);
        Assertions.assertEquals(card3, hand.getCard(2));
    }

    @Test
    @DisplayName("Sjekker om hånden er tom")
    public void testIsHandEmpty() {
        hand.drawToHand(card1);
        Assertions.assertEquals(false, hand.isHandEmpty());
        hand.drawFromHand(0);
        Assertions.assertEquals(true, hand.isHandEmpty());
    }

}
