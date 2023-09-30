package prosjekt;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand = new ArrayList<>();
    
    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    public boolean isHandEmpty() {
        if (hand.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Card drawFromHand(int card) {    // Finner et angitt kort i hånda og fjerner det.
        Card drawnCard = hand.get(card);
        hand.remove(drawnCard);
        return drawnCard;
    }

    public void drawToHand(Card drawnCard) {    // Legger til et angitt kort i hånda.
        hand.add(drawnCard);
    }

} 
