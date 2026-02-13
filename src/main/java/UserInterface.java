public class UserInterface {

    private ProgramControl programControl;

    public UserInterface(ProgramControl programControl) {
        this.programControl = programControl;
    }

    public int run(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println(programControl.listAvailableFiles());
                return 0;
            } else if (args.length == 1) {
                System.out.println(programControl.displayFileByIndex(args[0]));
                return 0;
            } else if (args.length == 2) {
                System.out.println(programControl.displayFileByIndex(args[0]));
                return 0;
            } else {
                System.out.println("Error: Too many arguments.");
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 1;
        }

    }
}
