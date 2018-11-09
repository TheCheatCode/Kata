import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class WordSearchTest {
    @Test
    public void ReadFile_WhenEmptyFile_ReturnsEmptyString() {
        WordSearch wordSearch = new WordSearch();
        String result = wordSearch.ReadFile("EmptyFile.txt");
        assertEquals("", result);
    }
}
