/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static void main(String[] args) {

        ProgramControl programControl = new ProgramControl(FileHandler filehandler);

        try {
            // Case 1: No arguments → list files
            if (args.length == 0) {
                String fileList = programControl.listAvailableFiles();
                System.out.println(fileList);
            }

            // Case 2: One argument → display file
            else if (args.length == 1) {
                String fileNumber = args[0];
                String content = programControl.displayFileByIndex(fileNumber);
                System.out.println(content);
            }

            // Case 3: Two arguments → display file with custom key
            else if (args.length == 2) {
                String fileNumber = args[0];
                String keyFile = args[1];
                String content = programControl.displayFileByIndex(fileNumber);
                System.out.println(content);
            }

            // Case 4: Too many arguments
            else {
                System.out.println("Error: Too many arguments.");
                printUsage();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper method to show how to use the program
    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java topsecret");
        System.out.println("  java topsecret <fileNumber>");
        System.out.println("  java topsecret <fileNumber> <keyFile>");
    }
}
