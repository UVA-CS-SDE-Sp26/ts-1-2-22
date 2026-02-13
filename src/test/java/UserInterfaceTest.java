import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceTest {

    @Test
    void testRunWithNoArguments() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        String[] args = {};

        String result = programControl.run(args);

        assertNotNull(result);
        assertInstanceOf(String.class, result);
    }

    @Test
    void testRunWithValidIndex() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        String[] args = {"1"};

        String result = programControl.run(args);

        assertNotNull(result);
    }

    @Test
    void testRunWithInvalidIndex() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        String[] args = {"100"};

        String result = programControl.run(args);

        assertNotNull(result);
        assertInstanceOf(String.class, result);
    }

    @Test
    void testRunWithNonNumericInput() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        String[] args = {"abc"};

        String result = programControl.run(args);

        assertNotNull(result);
    }

    @Test
    void testListAvailableFilesDoesNotCrash() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        assertDoesNotThrow(() -> {
            programControl.listAvailableFiles();
        });
    }

    @Test
    void testDisplayFileByIndexDoesNotCrash() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        assertDoesNotThrow(() -> {
            programControl.displayFileByIndex("1");
        });
    }

    @Test
    void testDisplayFileByIndexInvalidInputDoesNotCrash() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        assertDoesNotThrow(() -> {
            programControl.displayFileByIndex("abc");
        });
    }
}
