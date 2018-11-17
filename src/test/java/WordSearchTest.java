import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

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
}
