import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class WordSearch {
    public static void main(String[] args){
        WordSearch wordSearch = new WordSearch();
        String userInput = " ";
        Scanner reader = new Scanner(System.in);
        String output = "";

        while (!(userInput.equals("") || userInput.equalsIgnoreCase("exit"))) {
            System.out.println("Exit by typing \"exit\".");
            System.out.println("Enter a file path to Word Search input, must end with \".txt\": ");

            userInput = reader.nextLine();

            String rawText = wordSearch.ReadFile(userInput);
            String[] words = wordSearch.ReadWords(rawText);
            char[][] grid = wordSearch.ReadGrid(rawText);

            output = wordSearch.SearchGrid(words, grid);

            System.out.println(output);
        }
    }

    public String ReadFile(String fileName) {
        String result;
        if (!fileName.endsWith("txt")) {
            return "File must end with \".txt\".";
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
            return "Specified file is empty.";
        }
        if (!result.contains("\n")) {
            return "Specified File does not contain a grid.";
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

        for (String word: words) {
            // add newline before each word except first
            if (!output.equals("")) {
                output += "\n";
            }

            // loop through grid
            while (y < grid.length) {
                while (x < grid.length) {
                    char firstLetter = word.charAt(0);

                    if (firstLetter == grid[y][x]) {
                        output += DirectionsPresearch(x, y, word, grid);
                    }

                    x++;
                }
                x = 0;
                y++;
            }
            // reset coordinates for next word
            x = 0;
            y = 0;
        }




        return output;
    }

    private String DirectionsPresearch(int x, int y, String word, char[][] grid) {
        String result = "";
        String searchResponse = "";

        // Check for one letter words (only used in test cases)
        if (word.length() > 1) {
            String direction = "";

            //Check if word can fit between first letter and rest of grid
            if ((grid.length - x) > (word.length() - 1)) {
                direction = "East";

                searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);
            }
            if ((grid.length - y) > (word.length() - 1)) {
                direction = "South";

                searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);

                //Check if word fits East then send to Southeast
                if ((grid.length - x) > (word.length() - 1)) {
                    direction = "Southeast";

                    searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);
                }
                if (x >= (word.length() - 1)) {
                    direction = "Southwest";

                    searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);
                }
            }
            if (x >= (word.length() - 1)) {
                direction = "West";

                searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);
            }
            if (y >= (word.length() - 1)) {
                direction = "North";

                searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);

                //Check if word fits East then send to Northeast
                if ((grid.length - x) > (word.length() - 1)) {
                    direction = "Northeast";

                    searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);
                }
                if (x >= (word.length() - 1)) {
                    direction = "Northwest";

                    searchResponse += SearchDirections(x, y, word.substring(1), grid, direction);
                }
            }
        } else {
            result += word + ": (" + x + "," + y + ")";
        }

        // Check if the rest of the word was found
        if (!searchResponse.equals("")) {
            result += word + ": (" + x + "," + y + ")";
            result += searchResponse;
        }

        return result;
    }

    private String SearchDirections(int x, int y, String remaining, char[][] grid, String direction) {
        char current = remaining.charAt(0);

        // move along grid based on direction to search
        switch (direction) {
            case "East":
                x++;
                break;
            case "South":
                y++;
                break;
            case "West":
                x--;
                break;
            case "North":
                y--;
                break;
            case "Southeast":
                x++;
                y++;
                break;
            case "Southwest":
                x--;
                y++;
                break;
            case "Northeast":
                x++;
                y--;
                break;
            case "Northwest":
                x--;
                y--;
                break;
            default:
                return "";
        }

        // return if letter isn't found at this grid location
        if (current != grid[y][x]) {
            return "";
        }
        if (remaining.length() == 1) {
            return ",(" + x + "," + y + ")";
        }

        // remove first character and send back to SearchDirections
        remaining = remaining.substring(1);
        String next = SearchDirections(x, y, remaining, grid, direction);

        // pass empty string back up recursive calls if entire word isn't found
        if (next.equals("")) {
            return "";
        }

        return ",(" + x + "," + y + ")" + next;
    }

}
