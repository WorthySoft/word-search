package solutions.worthysoft.wordsearch;

import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        try {
            ImportFile file = new ImportFile(args[0]);
            Grid grid = new Grid(file.getValues());
            List<String> wordsToFind = file.getWords();
            for (String word : wordsToFind) {
                List<Letter> bookends = grid.find(word);
                if (bookends.size() == 2) {
                    System.out.print(word + " ");
                    System.out.println(bookends.get(0) + " " + bookends.get(1));
                }
            }
        } catch (Throwable t) {
            System.out.println(t);
        }
    }
}
