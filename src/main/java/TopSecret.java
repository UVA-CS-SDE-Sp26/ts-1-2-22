/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args) {
        // Create the components
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        UserInterface userInterface = new UserInterface(programControl);

        // Run the program
        int exitCode = userInterface.run(args);

        // Exit with appropriate code
        System.exit(exitCode);
    }
}
