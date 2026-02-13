// Megha Saikrishnan (hfe5ps), Member B, FileHandlerTest, Group 22
// Only testing .txt files as FileHandler handles and reads only plain text
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class FileHandlerTest {
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        fileHandler = new FileHandler();
    }
// finding if both files in data folder exist (carnivore and cointelpro)
    @Test
    @DisplayName("Should find carnivore.txt and cointelpro.txt")
    public void testBothFilesExist() {
        List<String> files = fileHandler.getFileList();

        assertTrue(files.contains("carnivore.txt"),
                "Should find carnivore.txt in data folder");
        assertTrue(files.contains("cointelpro.txt"),
                "Should find cointelpro.txt in data folder");
    }
// seeing if they appear in alphabetical order
    @Test
    @DisplayName("Files should be in alphabetical order")
    public void testAlphabeticalOrder() {
        List<String> files = fileHandler.getFileList();

        int carnivoreIndex = files.indexOf("carnivore.txt");
        int cointelproIndex = files.indexOf("cointelpro.txt");

        assertTrue(carnivoreIndex < cointelproIndex,
                "carnivore.txt should come before cointelpro.txt alphabetically");
    }
// seeing if FileHandler parses/reads carnivore.txt without any errors
    @Test
    @DisplayName("Should read carnivore.txt without errors")
    public void testReadCarnivore() {
        assertDoesNotThrow(() -> {
            String content = fileHandler.readFile("carnivore.txt");
            assertNotNull(content, "content should not be null");
            assertFalse(content.isEmpty(), "carnivore.txt should not be empty");
        });
    }
// seeing if FileHandler parses/reads cointelpro.txt without any errors
    @Test
    @DisplayName("Should read cointelpro.txt without errors")
    public void testReadCointelpro() {
        assertDoesNotThrow(() -> {
            String content = fileHandler.readFile("cointelpro.txt");
            assertNotNull(content, "content should not be null");
            assertFalse(content.isEmpty(), "cointelpro.txt should not be empty");
        });
    }
// validating if FileHandler reads both files in sequence
    @Test
    @DisplayName("Should read both files in sequence")
    public void testReadSequentially() throws Exception {
        String carnivoreContent = fileHandler.readFile("carnivore.txt");
        String cointelproContent = fileHandler.readFile("cointelpro.txt");

        assertNotNull(carnivoreContent);
        assertNotNull(cointelproContent);
        assertFalse(carnivoreContent.isEmpty());
        assertFalse(cointelproContent.isEmpty());
    }
// checking if FileHandler correctly identifies the amount of
    @Test
    @DisplayName("File list should have exactly 2 files")
    public void testExactlyTwoFiles() {
        List<String> files = fileHandler.getFileList();

        assertEquals(2, files.size(),
                "Should have exactly 2 .txt files in data folder");
    }

    @Test
    @DisplayName("List then read by index")
    public void testListAndRead() throws Exception {
        // This simulates how the main program will work
        List<String> files = fileHandler.getFileList();

        // test when user selecting file "01"
        if (files.size() > 0) {
            String firstFile = files.get(0); // index 0 = file "01"
            String content = fileHandler.readFile(firstFile);
            assertNotNull(content);
        }

        // test when user selecting file "02"
        if (files.size() > 1) {
            String secondFile = files.get(1); // index 1 = file "02"
            String content = fileHandler.readFile(secondFile);
            assertNotNull(content);
        }
    }
}