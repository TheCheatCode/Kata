import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

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
}
