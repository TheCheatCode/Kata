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
        int x = 0;
        int y = 0;

        while (y < grid.length) {
            while (x < grid.length) {
                String word = words[0];
                char firstLetter = word.charAt(0);

                if (firstLetter == grid[y][x]) {
                    output = words[0] + ": (" + x + "," + y + ")";
                    output += SearchEast(x + 1, y, word.substring(1), grid);
                }

                x++;
            }
            x = 0;
            y++;
        }




        return output;
    }

    private String SearchEast(int x, int y, String remaining, char[][] grid) {

        if (remaining.length() == 1) {
            return ",(" + x + "," + y + ")";
        }

        return "";
    }

}
