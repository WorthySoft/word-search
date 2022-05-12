package solutions.worthysoft.wordsearch;

import java.util.List;
import java.util.ArrayList;

/*
 * This class represents a full-length ordered sequence of characters
 * within a grid.
 */
public class Sequence {

    //This represents the first letter in the sequence
    private Letter first = null;

    //This represents the last letter in the sequence
    private Letter last = null;

    //This represents all the letters of the sequence in order
    private List<Letter> letters = new ArrayList<Letter>();

    //This represents a concatenated string representation of
    //letters in the sequence.
    private String value = "";

    public void appendLetter(Letter letter) {
        letters.add(letter);
        first = letters.get(0);
        last = letters.get(letters.size() - 1);
        value = "";
        for (Letter l : letters) {
            value += l.getValue();
        }
    }

    public void prependLetter(Letter letter) {
        letters.add(0, letter);
        first = letters.get(0);
        last = letters.get(letters.size() - 1);
        value = "";
        for (Letter l : letters) {
            value += l.getValue();
        }
    }

    public String getValue() {
        return value;
    }

    public Letter getFirst() {
        return first;
    }

    public Letter getLast() {
        return last;
    }

    //Locates a word in the sequence, if present,
    //returning list of first and last letter. If 
    //not present, returns empty list.
    public List<Letter> find(String word) {
        List<Letter> bookends = new ArrayList<Letter>();
        int start = value.indexOf(word);
        int offset = word.length() - 1;

        if (start != -1) {
            bookends.add(letters.get(start));
            bookends.add(letters.get(start + offset));
        }

        return bookends;
    }

    @Override
    public String toString() {
        return "(" + value + ", " + first + ", " + last + ")";
    }

}