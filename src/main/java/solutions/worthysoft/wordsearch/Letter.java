package solutions.worthysoft.wordsearch;

/*
 * This class represents a letter within a grid that includes its coordinates.
 */
public class Letter {

    private int x;
    private int y;
    private char value;

    public Letter(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return x + ":" + y;
    }

}