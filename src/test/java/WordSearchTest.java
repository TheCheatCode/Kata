import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class WordSearchTest {
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

        String[] result = wordSearch.ReadWords("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA\n" +
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
                "K,Y,L,B,Q,Q,P,M,D,F,C,K,E,A,B");

        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void ReadGrid_WhenOneLine_ReturnsEmptyArray() {
        char[][] expected = {{}};

        char[][] result = wordSearch.ReadGrid("Orange");

        if (expected.length != result.length) {
            fail("Expected and result are of different lengths");
        }
        for (int row = 0; row < expected.length; row++) { // loop through rows of grid
            boolean expectedEqualsResult = Arrays.equals(expected[row], result[row]);
            if (!expectedEqualsResult) {
                fail("Row " + row + " does not match.");
            }
        }
    }

    @Test
    public void ReadGrid_WhenTwoLinesOneChar_ReturnsOneByOneGrid() {
        char[][] expected = {{'A'}};

        char[][] result = wordSearch.ReadGrid("Orange\nA");

        if (expected.length != result.length) {
            fail("Expected and result are of different lengths");
        }
        for (int row = 0; row < expected.length; row++) { // loop through rows of grid
            boolean expectedEqualsResult = Arrays.equals(expected[row], result[row]);
            if (!expectedEqualsResult) {
                fail("Row " + row + " does not match.");
            }
        }
    }
}
