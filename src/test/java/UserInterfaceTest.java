import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceTest {
    @Mock
    private ProgramControl mockProgramControl;

    private UserInterface userInterface;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userInterface = new UserInterface(mockProgramControl);
    }
    @Test
    void testNoArguments() {
        // Arrange
        String[] args = {};

        // Act
        userInterface.run(args);

        // verify() checks that the listFiles() method was called on your mock
        //This proves your UI correctly delegates to program control
        verify(mockProgramControl).listFiles();
    }
    @Test

    @Test
    void testSingleArgument() {  // Arrange
        //Key point: Second parameter is null because no custom key was provided
        String[] args = {"01"};

        // Act
        userInterface.run(args);

        // Assert
        verify(mockProgramControl).displayFile("01", null); }

    @Test
    void testTwoArguments() { // Arrange
        String[] args = {"02", "customkey.txt"};

        // Act
        userInterface.run(args);

        // Assert
        verify(mockProgramControl).displayFile("02", "customkey.txt");}

    @Test
    void testInvalidInput() {   // Arrange
        String[] args = {"abc"};

        // Act
        userInterface.run(args);

        // Assert
        verify(mockProgramControl, never()).displayFile(anyString(), anyString());
        // Could also verify error message was printed }

        @Test
        void testProgramExitsAfterListingFiles() {
            // Arrange
            String[] args = {};

            // Act
            int exitCode = userInterface.run(args);

            // Assert
            assertEquals(0, exitCode);
        }}