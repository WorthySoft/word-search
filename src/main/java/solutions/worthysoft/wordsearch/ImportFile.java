package solutions.worthysoft.wordsearch;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;

/*
 * This class parses a file and extracts the word search grid and
 * the words that are to be looked for.
 */
public class ImportFile extends File {

    private char[][] values = null;
    private List<String> words = new ArrayList<String>();

    public ImportFile(String fileLocation) {
        super(fileLocation);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(this);
            byte[] fileBytes = fis.readAllBytes();
            String contents = new String(fileBytes);

            //Windows uses \r\n couplet to indicate carriage return and line feed
            String[] lines = null;
            if (contents.indexOf("\r\n") != -1) {
                lines = contents.split("\r\n");
            } else {
                lines = contents.split("\n");
            }

            //First line is dimensions
            String[] dimensions = lines[0].split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            //Next lines are matrix of letters
            values = new char[height][width];
            for (int row=1; row<=height; row++) {
                String[] chars = lines[row].split(" ");
                for (int col=0; col<width; col++) {
                    values[row-1][col] = chars[col].charAt(0);
                }
            }

            //Final lines are words to be found
            for (int row=height+1; row<lines.length; row++) {
                words.add(lines[row]);
            }

        } catch (Throwable t) {
            System.out.println(t);
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (Throwable t) {
                System.out.println(t);
            }
        }
    }

    public char[][] getValues() {
        return values;
    }

    public List<String> getWords() {
        return words;
    }
}
