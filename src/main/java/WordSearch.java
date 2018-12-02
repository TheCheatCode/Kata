import java.io.BufferedReader;
import java.io.FileReader;

public class WordSearch {
    public String ReadFile(String fileName) {
        String result;
        if (!fileName.endsWith("txt")) {
            return "";
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            result = reader.readLine();

            while ((currentLine = reader.readLine()) != null) {

                result += "\n" + currentLine;
            }
            reader.close();

        } catch (Exception e) {
            return e.getMessage();
        }

        if (result == null) {
            result = "";
        }

        return result;
    }

    public String[] ReadWords(String input) {
        String[] lines = input.split("\n");
        String[] words = lines[0].split(",");

        return words;
    }

    public char[][] ReadGrid(String input) {
        String[] rows = input.split("\n");
        if (rows.length < 2) {
            char[][] a = {{}};
            return a;
        }

        // create square grid based on number of lines in input
        char[][] grid = new char[rows.length - 1][rows.length - 1];

        for (int row = 1; row < rows.length; row++) {
            // remove commas from string
            rows[row] = rows[row].replaceAll(",", "");

            // convert each line to char array and add to the grid
            grid[row - 1] = rows[row].toCharArray();
        }

        return grid;
    }

    public String SearchGrid(String[] words, char[][] grid) {
        String output = "";

        for (String word: words) {
            if (!output.equals("")) {
                output += "\n";
            }

            output += SearchEast(word, grid);
        }

        return output;
    }

    private String SearchEast(String word, char[][] grid) {
        String row;
        String result = "";
        int xLoc = -1;
        int yLoc = -1;

        int i = 0;
        while ((xLoc == -1) && (i < grid.length)) { // xLoc will be -1 until word is found
            row = new String(grid[i]);
            xLoc = row.indexOf(word);

            yLoc = i;

            i++;
        }

        result = FormatString(xLoc, yLoc, word);

        return result;
    }

    private String FormatString(int xLoc, int yLoc, String word) {
        String formattedResult = word + ": (" + xLoc + "," + yLoc + ")";

        for (int i = 1; i < word.length(); i++) {
            formattedResult += ",(" + (xLoc + i) + "," + yLoc + ")";
        }

        return formattedResult;
    }
}
