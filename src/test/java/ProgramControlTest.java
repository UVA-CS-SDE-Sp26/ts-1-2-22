
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProgramControlTest {

    @Test
    void listsFilesWhenNoArgumentsProvided() {
        FileHandler mockHandler = Mockito.mock(FileHandler.class);
        when(mockHandler.getFileList())
                .thenReturn(List.of("filea.txt", "fileb.txt"));

        ProgramControl control = new ProgramControl(mockHandler);
        String output = control.run(new String[]{});

        assertTrue(output.contains("01 filea.txt"));
        assertTrue(output.contains("02 fileb.txt"));
    }

    @Test
    void displaysFileContentsForValidIndex() throws Exception {
        FileHandler mockHandler = Mockito.mock(FileHandler.class);
        when(mockHandler.getFileList())
                .thenReturn(List.of("filea.txt"));
        when(mockHandler.readFile("filea.txt"))
                .thenReturn("Top Secret Content");

        ProgramControl control = new ProgramControl(mockHandler);
        String output = control.run(new String[]{"1"});

        assertEquals("Top Secret Content", output);
    }

    @Test
    void returnsErrorForInvalidIndex() {
        FileHandler mockHandler = Mockito.mock(FileHandler.class);
        when(mockHandler.getFileList())
                .thenReturn(List.of("filea.txt"));

        ProgramControl control = new ProgramControl(mockHandler);
        String output = control.run(new String[]{"5"});

        assertTrue(output.contains("Error"));
    }

    @Test
    void returnsErrorForNonNumericArgument() {
        FileHandler mockHandler = Mockito.mock(FileHandler.class);

        ProgramControl control = new ProgramControl(mockHandler);
        String output = control.run(new String[]{"abc"});

        assertTrue(output.contains("Error"));
    }
}
