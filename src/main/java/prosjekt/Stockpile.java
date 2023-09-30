package prosjekt;

import java.util.ArrayList;
import java.util.List;

public class Stockpile {

    private List<Card> pile = new ArrayList<>();

    public List<Card> getPile() {
        return pile;
    }

    public int getPileSize() {
        return pile.size();
    }
    
    public Card getTopCard() {      // Finner det øverste kortet i bunken med kort.
        Card topCard = pile.get(getPileSize() - 1);
        return topCard;
    }
    
    public boolean isPileEmpty() {
        if (pile.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addToPile(Card drawnCard) {     // Legger til et angitt kort i bunken.
        pile.add(drawnCard);
    }
    
}
