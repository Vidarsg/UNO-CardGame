package prosjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class UnoGameController {

    @FXML private Button deal;
    @FXML private ImageView cardback;
    @FXML private ImageView pileBorder;
    @FXML private ImageView pileTopCard;
    @FXML private Label playerName;
    @FXML private Label wins;
    @FXML private Label losses;
    @FXML private Label draws;
    @FXML private HBox playerHand;
    @FXML private HBox opponentHand;

    @FXML private ImageView playerCard1; @FXML private ImageView playerCard2; @FXML private ImageView playerCard3; @FXML private ImageView playerCard4; @FXML private ImageView playerCard5; @FXML private ImageView playerCard6;
    @FXML private ImageView playerCard7; @FXML private ImageView playerCard8; @FXML private ImageView playerCard9; @FXML private ImageView playerCard10; @FXML private ImageView playerCard11; @FXML private ImageView playerCard12;

    @FXML private ImageView opponentCard1; @FXML private ImageView opponentCard2; @FXML private ImageView opponentCard3; @FXML private ImageView opponentCard4; @FXML private ImageView opponentCard5; @FXML private ImageView opponentCard6;
    @FXML private ImageView opponentCard7; @FXML private ImageView opponentCard8; @FXML private ImageView opponentCard9; @FXML private ImageView opponentCard10; @FXML private ImageView opponentCard11;  @FXML private ImageView opponentCard12;

    @FXML private List<ImageView> playerCardList = new ArrayList<ImageView>();
    @FXML private List<ImageView> opponentCardList = new ArrayList<ImageView>();

    private Hand player;
    private Hand opponent;
    private Deck deck;
    private Stockpile pile;
    private Results results;

    private final static String imagePath = "file:src/main/resources/prosjekt/images/";
    private final static String textFile = "src/main/resources/prosjekt/results.txt";

    public static String getTextFile() {
        return textFile;
    }

    public void setPlayerName(String name) {    // Henter brukernavnet fra UnoMenuController.java og viser det i UnoGameController.java.
        playerName.setText(name);
    }

    public void initialize() {  // Alt som står under initialize() skjer når UnoGameController.java åpner.

        results = new Results();

        cardback.setDisable(true);
        pileBorder.setVisible(false);

        playerCardList.addAll(Arrays.asList(playerCard1, playerCard2, playerCard3, playerCard4, playerCard5,
        playerCard6, playerCard7, playerCard8, playerCard9, playerCard10, playerCard11, playerCard12));

        opponentCardList.addAll(Arrays.asList(opponentCard1, opponentCard2, opponentCard3, opponentCard4, opponentCard5, 
        opponentCard6, opponentCard7, opponentCard8, opponentCard9, opponentCard10, opponentCard11, opponentCard12));
    }
    
    public void deal() {    // Starter et nytt spill ved å trekke syv kort til hver spiller samt ett kort til bunken.

        deck = new Deck(9);
        deck.shuffleDeck();
        pile = new Stockpile();
        player = new Hand();
        opponent = new Hand();

        for (int i = 0; i < 7; i++) {
            drawFromDeckToPlayer();
            drawFromDeckToOpponent();
        }
        drawFromDeckToPile();
        Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
        pileTopCard.setImage(pileCardImage);

        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
    }

    public void checkGameStatus() {     // Utføres dersom spillet er ferdig spilt og viser en alertbox med resultatet.
        if (player.isHandEmpty() && !opponent.isHandEmpty())  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("VICTORY");
            alert.setContentText("You won the game!");
            alert.show();
            results.writeResultsToFile("Win");   // Skriver også resultatet til fil og oppdaterer antall gitt resultat i appen.
            results.readResultsFromFile(textFile);
            wins.setText(results.getWins());
            cardback.setDisable(true);
            pileBorder.setVisible(false);
        }
        else if (opponent.isHandEmpty() && !player.isHandEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("DEFEAT");
            alert.setContentText("You lost the game.");
            alert.show();
            results.writeResultsToFile("Loss");
            results.readResultsFromFile(textFile);
            losses.setText(results.getLosses());
            cardback.setDisable(true);
            pileBorder.setVisible(false);
        }
        else if (player.isHandEmpty() && opponent.isHandEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("DRAW");
            alert.setContentText("You drew the game.");
            alert.show();
            results.writeResultsToFile("Draw");
            results.readResultsFromFile(textFile);
            draws.setText(results.getDraws());
            cardback.setDisable(true);
            pileBorder.setVisible(false);
        }
    }

    public void highlightDraw() {       // Viser en grønn ramme rundt kortstokken dersom du ikke har noen kort å spille.
        if (playerHasPlayableCard()) {
            pileBorder.setVisible(false);
            cardback.setDisable(true);
        } else {
            pileBorder.setVisible(true);
            cardback.setDisable(false);
        }
    }

    public void updatePlayerImageViews() {      // Oppdaterer bildene i playerCardList ut i fra hvor mange og hvilke kort du har i hånden.
        for (int i = 0; i < player.getHandSize(); i++) {
            Image playerCardImage = new Image(imagePath + player.getCard(i).toString() + ".png");
            playerCardList.get(i).setImage(playerCardImage);
        }
        for (int i = player.getHandSize(); i < playerCardList.size(); i++) {
            playerCardList.get(i).setImage(null);
        }

    }

    public void updateOpponentImageViews() {    // Oppdaterer bildene i opponentCardList ut i fra hvor mange og hvilke kort motstanderen har i hånden.
        for (int i = 0; i < opponent.getHandSize(); i++) {
            Image opponentCardImage = new Image(imagePath + "cardback.png");
            opponentCardList.get(i).setImage(opponentCardImage);
        }
        for (int i = opponent.getHandSize(); i < opponentCardList.size(); i++) {
            opponentCardList.get(i).setImage(null);
        }
    }

    public void drawFromDeckToPlayer() {    // Trekker et kort fra kortstokken til spilleren sin hånd.
        player.drawToHand(deck.drawFromDeck());
    }

    public void drawFromDeckToOpponent() {    // Trekker et kort fra kortstokken til motstanderen sin hånd.
        opponent.drawToHand(deck.drawFromDeck());
    }

    public void drawFromDeckToPile() {      // Trekker et kort fra kortstokken til bunken.
        pile.addToPile(deck.drawFromDeck());
    }

    public boolean isValidPlay(int index) {     // Sjekker om angitt kort er spillbart.
        if (player.getCard(index).getColor() == pile.getTopCard().getColor() || player.getCard(index).getNumber() == pile.getTopCard().getNumber()) {
            return true;
        }
        return false;  
    }

    public boolean playerHasPlayableCard() {    // Sjekker om spiller har noen spillbare kort i hånden.
        for (int i = 0; i < player.getHandSize(); i++) {
            if (isValidPlay(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean opponentHasPlayableCard() {    // Sjekker om motstander har noen spillbare kort i hånden.
        for (int i = 0; i < opponent.getHandSize(); i++) {
            if (opponent.getCard(i).getColor() == pile.getTopCard().getColor() || opponent.getCard(i).getNumber() == pile.getTopCard().getNumber()) {
                return true;
            }
        }
        return false;
    }

    public void playOpponentCard() {    // Motstander spiller det første kortet i hånden som kan spilles.
        for (int i = 0; i < opponent.getHandSize(); i++) {
            if (opponent.getCard(i).getColor() == pile.getTopCard().getColor() || opponent.getCard(i).getNumber() == pile.getTopCard().getNumber()) {
                pile.addToPile(opponent.drawFromHand(i));
                Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
                pileTopCard.setImage(pileCardImage);
                break;
            }
        }
    }

    public void draw() {
        drawFromDeckToPlayer();
        if (opponentHasPlayableCard()) {
            playOpponentCard();
        } else {
            drawFromDeckToOpponent();
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus();
    }

    public void playCard1() {
        if (isValidPlay(0)) {
            pile.addToPile(player.drawFromHand(0));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus();
    }   

    public void playCard2() {
        if (isValidPlay(1)) {
            pile.addToPile(player.drawFromHand(1));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus();
    }

    public void playCard3() {
        if (isValidPlay(2)) {
            pile.addToPile(player.drawFromHand(2));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus(); 
    }

    public void playCard4() {
        if (isValidPlay(3)) {
            pile.addToPile(player.drawFromHand(3));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus();
    }

    public void playCard5() {
        if (isValidPlay(4)) {
            pile.addToPile(player.drawFromHand(4));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews(); 
        highlightDraw();
        checkGameStatus();
    }

    public void playCard6() {
        if (isValidPlay(5)) {
            pile.addToPile(player.drawFromHand(5));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus(); 
    }

    public void playCard7() {
        if (isValidPlay(6)) {
            pile.addToPile(player.drawFromHand(6));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw(); 
        checkGameStatus();
    }

    public void playCard8() {
        if (isValidPlay(7)) {
            pile.addToPile(player.drawFromHand(7));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus();
    }

    public void playCard9() {
        if (isValidPlay(8)) {
            pile.addToPile(player.drawFromHand(8));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus();
    }

    public void playCard10() {
        if (isValidPlay(9)) {
            pile.addToPile(player.drawFromHand(9));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw(); 
        checkGameStatus();
    }

    public void playCard11() {
        if (isValidPlay(10)) {
            pile.addToPile(player.drawFromHand(10));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews();
        highlightDraw();
        checkGameStatus();
    }

    public void playCard12() {
        if (isValidPlay(11)) {
            pile.addToPile(player.drawFromHand(11));
            Image pileCardImage = new Image(imagePath + pile.getTopCard().toString() + ".png");
            pileTopCard.setImage(pileCardImage);
            if (opponentHasPlayableCard()) {
                playOpponentCard();
            } else {
                drawFromDeckToOpponent();
            }
        }
        updatePlayerImageViews();
        updateOpponentImageViews(); 
        highlightDraw();
        checkGameStatus();
    }

} 
