package prosjekt;

public class Card {
   
    private char color;
    private int number;

    public Card(char color, int number) {
        if (color == 'R' || color == 'Y' || color == 'B' || color == 'G') {
            this.color = color;
        } else {
            throw new IllegalArgumentException("Invalid color");
        }
        if (number <= 9 && number >= 0) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("Invalid number");
        }
    }

    public int getNumber() {
        return number;
    }

    public char getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color + "" + number;
    }

}
