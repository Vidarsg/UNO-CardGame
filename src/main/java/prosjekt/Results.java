package prosjekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Results implements ResultsInterface {

    private List<String> resultStrings = new ArrayList<String>();
    private int wins, losses, draws;

    public void writeResultsToFile(String text) {   // Skriver resultat til filen results.txt for hver string i listen resultStrings.
        resultStrings.add(text);
        try {
            PrintWriter writer = new PrintWriter(UnoGameController.getTextFile() + "");
            for (String string : resultStrings) {
                writer.println(string);
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void readResultsFromFile(String filename) {   // Leser gjennom hver linje i filen results.txt. 
        wins = 0;                                        // Øker wins/losses/draws for hver gang en linje inneholder "Win"/"Loss"/"Draw".
        losses = 0;
        draws = 0;
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Win")) {
                    wins++;
                } else if (line.contains("Loss")) {
                    losses++;
                } else if (line.contains("Draw")) {
                    draws++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public String getWins() {
        return wins + "";
    }

    public String getLosses() {
        return losses + "";
    }
    
    public String getDraws() {
        return draws + "";
    }
}
