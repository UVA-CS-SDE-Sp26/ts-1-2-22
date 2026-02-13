public class UserInterface {

    private ProgramControl programControl;

    public UserInterface(ProgramControl programControl) {
        this.programControl = programControl;
    }

    public int run(String[] args) {
        try {
            if (args.length == 0) {
                programControl.listAvailableFiles();
                return 0;
            } else if (args.length == 1) {
                programControl.displayFileByIndex(args[0]);
                return 0;
            } else if (args.length == 2) {
                programControl.displayFileByIndex(args[0]);
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }
}
