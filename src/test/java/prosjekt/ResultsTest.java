package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultsTest {

    private Results results;

    @BeforeEach
    public void setUp() {
        results = new Results();
    }

    @Test
    @DisplayName("Sjekker at filhåndteringen fungerer som den skal")
    public void testWriteAndReadResultsFromFile() {

        results.writeResultsToFile("Win");
        results.writeResultsToFile("Loss");
        results.writeResultsToFile("Loss");
        results.writeResultsToFile("Win");
        results.writeResultsToFile("Draw");
        results.writeResultsToFile("Win");
        results.readResultsFromFile(UnoGameController.getTextFile() + "");

        Assertions.assertEquals("3", results.getWins());
        Assertions.assertEquals("2", results.getLosses());
        Assertions.assertEquals("1", results.getDraws());
    }
}
