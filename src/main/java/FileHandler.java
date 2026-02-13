// Megha Saikrishnan, Team Member B, FileHandler implementation
import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String DATA_DIR = "data/";

    // returns a sorted list of .txt filenames (without numbers)
    public List<String> getFileList() {
        File folder = new File(DATA_DIR);
        List<String> fileList = new ArrayList<>();
    // filter for .txt files, case insensitive
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files != null) {
                for (File f : files) {
                    fileList.add(f.getName());
                }
                Collections.sort(fileList); // sort alphabetically
            }
        }

        return fileList; // empty list if no files or folder missing
    }

    // reads the contents of the specified file
    // parameter: fileName (name of file to read)
    public String readFile(String fileName) throws Exception {
        File file = new File(DATA_DIR + fileName);

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            // exceptions for file is not found or reading error
        } catch (FileNotFoundException e) {
            throw new Exception("File not found: " + fileName);
        } catch (IOException e) {
            throw new Exception("File reading error: " + fileName);
        }

        return content.toString();
    }
}
