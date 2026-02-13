
import java.util.List;

public class ProgramControl {

    private final FileHandler fileHandler;

    public ProgramControl(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public String run(String[] args) {
        if (args.length == 0) {
            return listAvailableFiles();
        }

        return displayFileByIndex(args[0]);
    }

    public String listAvailableFiles() {
        List<String> files = fileHandler.getFileList();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < files.size(); i++) {
            output.append(String.format("%02d %s%n", i + 1, files.get(i)));
        }

        return output.toString();
    }

    public String displayFileByIndex(String indexArg) {
        try {
            int index = Integer.parseInt(indexArg) - 1;
            List<String> files = fileHandler.getFileList();

            if (index < 0 || index >= files.size()) {
                return "Error: Invalid file selection.";
            }

            String filename = files.get(index);
            return fileHandler.readFile(filename);

        } catch (NumberFormatException e) {
            return "Error: File selection must be a number.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
