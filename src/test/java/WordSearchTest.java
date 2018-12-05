import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class WordSearchTest {
    private static final String EXAMPLE_INPUT_ONE = "BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA\n" +
            "U,M,K,H,U,L,K,I,N,V,J,O,C,W,E\n" +
            "L,L,S,H,K,Z,Z,W,Z,C,G,J,U,Y,G\n" +
            "H,S,U,P,J,P,R,J,D,H,S,B,X,T,G\n" +
            "B,R,J,S,O,E,Q,E,T,I,K,K,G,L,E\n" +
            "A,Y,O,A,G,C,I,R,D,Q,H,R,T,C,D\n" +
            "S,C,O,T,T,Y,K,Z,R,E,P,P,X,P,F\n" +
            "B,L,Q,S,L,N,E,E,E,V,U,L,F,M,Z\n" +
            "O,K,R,I,K,A,M,M,R,M,F,B,A,P,P\n" +
            "N,U,I,I,Y,H,Q,M,E,M,Q,R,Y,F,S\n" +
            "E,Y,Z,Y,G,K,Q,J,P,C,Q,W,Y,A,K\n" +
            "S,J,F,Z,M,Q,I,B,D,B,E,M,K,W,D\n" +
            "T,G,L,B,H,C,B,E,C,H,T,O,Y,I,K\n" +
            "O,J,Y,E,U,L,N,C,C,L,Y,B,Z,U,H\n" +
            "W,Z,M,I,S,U,K,U,R,B,I,D,U,X,S\n" +
            "K,Y,L,B,Q,Q,P,M,D,F,C,K,E,A,B";

    WordSearch wordSearch = new WordSearch();
    // Converting resource files into absolute path for test relevance
    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    public void ReadFile_WhenEmptyFile_ReturnsEmptyString() {
        File file = new File(classLoader.getResource("EmptyFile.txt").getFile());

        String result = wordSearch.ReadFile(file.getAbsolutePath());

        assertEquals("", result);
    }

    @Test
    public void ReadFile_WhenOneLineFile_ReturnsContent() {
        File file = new File(classLoader.getResource("OneLineFile.txt").getFile());

        String result = wordSearch.ReadFile(file.getAbsolutePath());

        assertEquals("One line example.", result);
    }

    @Test
    public void ReadFile_WhenMultiLineFile_ReturnsContent() {
        File file = new File(classLoader.getResource("MultiLineFile.txt").getFile());

        String result = wordSearch.ReadFile(file.getAbsolutePath());

        assertEquals("Multi line\nexample.\nMulti\nline.", result);
    }

    @Test
    public void ReadFile_WhenNonTxtFile_ReturnsEmptyString() {
        String result = wordSearch.ReadFile("NonTxtFile.exe");

        assertEquals("", result);
    }

    @Test
    public void ReadFile_WhenFileNotFound_ReturnsErrorMessage() {
        String result = wordSearch.ReadFile("FileNotFound.txt");

        assertEquals("FileNotFound.txt (The system cannot find the file specified)", result);
    }

    @Test
    public void ReadWords_WhenOneWord_ReturnsOneWord() {
        String[] expected = {"One"};

        String[] result = wordSearch.ReadWords("One");

        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void ReadWords_WhenMultipleWords_ReturnsWordArray() {
        String[] expected = {"One","Two","Three","Four"};

        String[] result = wordSearch.ReadWords("One,Two,Three,Four");

        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void ReadWords_WhenMultiWordTwoLine_ReturnsWordArray() {
        String[] expected = {"First","Second","Third"};

        String[] result = wordSearch.ReadWords("First,Second,Third\nA,B,C");

        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void ReadWords_WhenOneWordTwoLine_ReturnsWordArray() {
        String[] expected = {"Word"};

        String[] result = wordSearch.ReadWords("Word\nX,Y,Z");

        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void ReadWords_WhenFullWordSearchInput_ReturnsWordArray() {
        String[] expected = {"BONES","KHAN","KIRK","SCOTTY","SPOCK","SULU","UHURA"};

        String[] result = wordSearch.ReadWords(EXAMPLE_INPUT_ONE);

        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void ReadGrid_WhenOneLine_ReturnsEmptyArray() {
        char[][] expected = {{}};

        char[][] result = wordSearch.ReadGrid("Orange");

        CompareGrids(expected, result);
    }

    @Test
    public void ReadGrid_WhenTwoLinesOneChar_ReturnsOneByOneGrid() {
        char[][] expected = {{'A'}};

        char[][] result = wordSearch.ReadGrid("Orange\nA");

        CompareGrids(expected, result);
    }

    @Test
    public void ReadGrid_WhenThreeLinesTwoChar_ReturnsTwoByTwoGrid() {
        char[][] expected = {{'B','A'},{'D','C'}};

        char[][] result = wordSearch.ReadGrid("Orange\nB,A\nD,C");

        CompareGrids(expected, result);
    }

    @Test
    public void ReadGrid_WhenFullWordSearchInput_ReturnsGrid() {
        char[][] expected = {{'U','M','K','H','U','L','K','I','N','V','J','O','C','W','E'},
                {'L','L','S','H','K','Z','Z','W','Z','C','G','J','U','Y','G'},
                {'H','S','U','P','J','P','R','J','D','H','S','B','X','T','G'},
                {'B','R','J','S','O','E','Q','E','T','I','K','K','G','L','E'},
                {'A','Y','O','A','G','C','I','R','D','Q','H','R','T','C','D'},
                {'S','C','O','T','T','Y','K','Z','R','E','P','P','X','P','F'},
                {'B','L','Q','S','L','N','E','E','E','V','U','L','F','M','Z'},
                {'O','K','R','I','K','A','M','M','R','M','F','B','A','P','P'},
                {'N','U','I','I','Y','H','Q','M','E','M','Q','R','Y','F','S'},
                {'E','Y','Z','Y','G','K','Q','J','P','C','Q','W','Y','A','K'},
                {'S','J','F','Z','M','Q','I','B','D','B','E','M','K','W','D'},
                {'T','G','L','B','H','C','B','E','C','H','T','O','Y','I','K'},
                {'O','J','Y','E','U','L','N','C','C','L','Y','B','Z','U','H'},
                {'W','Z','M','I','S','U','K','U','R','B','I','D','U','X','S'},
                {'K','Y','L','B','Q','Q','P','M','D','F','C','K','E','A','B'}};

        char[][] result = wordSearch.ReadGrid(EXAMPLE_INPUT_ONE);

        CompareGrids(expected, result);
    }

    @Test
    public void SearchGrid_WhenOneLetterTwoSquare_ReturnsLocation() {
        String expected = "A: (0,0)";
        String[] words = {"A"};
        char[][] grid = {{'A','B'},{'C','D'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenOneLetterThreeSquare_ReturnsLocation() {
        String expected = "M: (2,0)";
        String[] words = {"M"};
        char[][] grid = {{'D','T','M'},{'A','B','C'},{'R','Q','P'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenOneLetterThreeSquareMiddleColumn_ReturnsLocation() {
        String expected = "T: (1,0)";
        String[] words = {"T"};
        char[][] grid = {{'D','T','M'},{'A','B','C'},{'R','Q','P'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenOneLetterThreeSquareMiddleRow_ReturnsLocation() {
        String expected = "A: (0,1)";
        String[] words = {"A"};
        char[][] grid = {{'D','T','M'},{'A','B','C'},{'R','Q','P'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenTwoLetterTwoSquare_ReturnsLocation() {
        String expected = "IT: (0,0),(1,0)";
        String[] words = {"IT"};
        char[][] grid = {{'I','T'},{'A','B'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenTwoLetterTwoSquareLastRow_ReturnsLocation() {
        String expected = "AB: (0,1),(1,1)";
        String[] words = {"AB"};
        char[][] grid = {{'I','T'},{'A','B'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenTwoLetterTwoSquareFirstLetterBeforeWord_ReturnsLocation() {
        String expected = "AB: (0,1),(1,1)";
        String[] words = {"AB"};
        char[][] grid = {{'A','T'},{'A','B'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenTwoLetterTwoSquareFirstLetterAfterWord_ReturnsLocation() {
        String expected = "IT: (0,0),(1,0)";
        String[] words = {"IT"};
        char[][] grid = {{'I','T'},{'I','B'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    /*@Test
    public void SearchGrid_WhenThreeLetterThreeSquare_ReturnsLocation() {
        String expected = "CAT: (0,0),(1,0),(2,0)";
        String[] words = {"CAT"};
        char[][] grid = {{'C','A','T'},{'A','B','C'},{'D','E','F'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenTwoLetterTwoSquareSecondRow_ReturnsLocation() {
        String expected = "BE: (0,1),(1,1)";
        String[] words = {"BE"};
        char[][] grid = {{'C','T'},{'B','E'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }

    @Test
    public void SearchGrid_WhenMultipleTwoLetterWordsThreeSquare_ReturnsLocation() {
        String expected = "IS: (1,0),(2,0)\nDO: (0,2),(1,2)";
        String[] words = {"IS","DO"};
        char[][] grid = {{'C','I','S'},{'A','B','C'},{'D','O','F'}};

        String result = wordSearch.SearchGrid(words, grid);

        assertEquals(expected, result);
    }*/

    private void CompareGrids(char[][] expected, char[][] result) {
        if (expected.length != result.length) {
            fail("Expected length: " + expected.length +
                    ". Result length: " + result.length);
        }
        for (int row = 0; row < expected.length; row++) { // loop through rows of grid
            boolean expectedEqualsResult = Arrays.equals(expected[row], result[row]);
            if (!expectedEqualsResult) {
                fail("Expected row " + row + ": " + new String(expected[row])
                + ". Result row " + row + ": " + new String(result[row]));
            }
        }
    }
}
