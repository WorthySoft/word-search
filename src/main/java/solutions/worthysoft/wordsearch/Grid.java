package solutions.worthysoft.wordsearch;

import java.util.ArrayList;
import java.util.List;

/*
 * This class represents the grid of letters that we will search. The grid
 * will search itself for indicated words by finding the words in (if present)
 * in an array of all possible legitimate full-length letter sequences, 
 * which are built upon construction of the grid.
 */
public class Grid {

    private List<Sequence> sequences = new ArrayList<Sequence>();

    public Grid(char[][] values) {

        int numRows = values.length;
        int numColumns = values[0].length;

        //Find all of the possible legitimate full-length HORIZONTAL letter sequences
        for (int x=0; x<numRows; x++) {
            Sequence lr = new Sequence();
            Sequence rl = new Sequence();
            for (int y=0; y<numColumns; y++) {
                Letter l = new Letter(x, y, values[x][y]);
                lr.appendLetter(l);
                rl.prependLetter(l);
            }
            sequences.add(lr);
            sequences.add(rl);
        }

        //Find all of the possible legitimate full-length VERTICAL letter sequences
        for (int y=0; y<numColumns; y++) {
            Sequence td = new Sequence();
            Sequence bu = new Sequence();
            for (int x=0; x<numRows; x++) {
                Letter l = new Letter(x, y, values[x][y]);
                td.appendLetter(l);
                bu.prependLetter(l);
            }
            sequences.add(td);
            sequences.add(bu);
        }

        //Find all of the possible legitimate full-length  
        //DIAGONAL NW/SE letter sequences
        for (int yStart=0; yStart<numColumns; yStart++) {
            Sequence sqNwSe = new Sequence();
            Sequence sqSeNw = new Sequence();
            for (int x=0, y=yStart; x<numRows && y<numColumns; x++, y++) {
                Letter l = new Letter(x, y, values[x][y]);
                sqNwSe.appendLetter(l);
                sqSeNw.prependLetter(l);
            }
            sequences.add(sqNwSe);
            sequences.add(sqSeNw);
        }

        for (int xStart=1; xStart<numRows; xStart++) {
            Sequence sqNwSe = new Sequence();
            Sequence sqSeNw = new Sequence();
            for (int x=xStart, y=0; x<numRows && y<numColumns; x++, y++) {
                Letter l = new Letter(x, y, values[x][y]);
                sqNwSe.appendLetter(l);
                sqSeNw.prependLetter(l);
            }
            sequences.add(sqNwSe);
            sequences.add(sqSeNw);
        }

        //Find all of the possible legitimate full-length  
        //DIAGONAL NE/SW letter sequences
        for (int yStart=(numColumns - 1); yStart>=0; yStart--) {
            Sequence sqNeSw = new Sequence();
            Sequence sqSwNe = new Sequence();
            for (int x=0, y=yStart; x<numRows && y>=0; x++, y--) {
                Letter l = new Letter(x, y, values[x][y]);
                sqNeSw.appendLetter(l);
                sqSwNe.prependLetter(l);
            }
            sequences.add(sqNeSw);
            sequences.add(sqSwNe);
        }

        for (int xStart=1; xStart<numRows; xStart++) {
            Sequence sqNeSw = new Sequence();
            Sequence sqSwNe = new Sequence();
            for (int x=xStart, y=(numColumns-1); x<numRows && y>=0; x++, y--) {
                Letter l = new Letter(x, y, values[x][y]);
                sqNeSw.appendLetter(l);
                sqSwNe.prependLetter(l);
            }
            sequences.add(sqNeSw);
            sequences.add(sqSwNe);
        }
    }

    public String toString() {
        String retVal = "";
        for (Sequence sequence : sequences) {
            retVal += sequence + "\n";
        }
        return retVal;
    }

    public List<Letter> find(String word) {
        for (Sequence sequence : sequences) {
            List<Letter> bookends = sequence.find(word);
            if (!bookends.isEmpty()) {
                return bookends;
            }
        }

        return new ArrayList<Letter>(); //return empty list
    }
}