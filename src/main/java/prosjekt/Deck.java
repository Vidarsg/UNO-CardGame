package prosjekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    
    private List<Card> deck = new ArrayList<>();

    public Deck(int cards) {
        for (int j = 1; j <= 2; j++) {
            if (cards <= 9 && cards >= 0) {
                for (int i = 0; i <= cards; i++) {
                    deck.add(new Card('R', i));
                }
                for (int i = 0; i <= cards; i++) {
                    deck.add(new Card('Y', i));
                }
                for (int i = 0; i <= cards; i++) {
                    deck.add(new Card('B', i));
                }
                for (int i = 0; i <= cards; i++) {
                    deck.add(new Card('G', i));
                }
            } else {
                throw new IllegalArgumentException("Invalid amount");
            }
        } 
    }

    public int getDeckSize() {
        return deck.size();
    } 

    public boolean isDeckEmpty() {
        if (deck.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);  // Sorterer kortstokken vilkårlig.
    }

    public Card drawFromDeck() {    // Finner det øverste kortet i kortstokken og fjerner det.
        if (!isDeckEmpty()) {
            Card drawnCard = deck.get(getDeckSize() - 1);   
            deck.remove(drawnCard);     
            return drawnCard;
        } else {
            throw new IllegalStateException("The deck is empty!");
        }  
    }
    
}
