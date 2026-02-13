// Megha Saikrishnan, Team Member B, FileHandler implementation
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileHandler {
    private static final String DATA_DIR = "data" + File.separator;

    // returns a sorted list of .txt filenames (without numbers)
    public List<String> getFileList() {
        Path targetFilePath = Paths.get(DATA_DIR);
        //check if file exists, use the folder
        File folder = new File(DATA_DIR);
        if(targetFilePath.toFile().exists()) {
             folder = targetFilePath.toFile();
        }else{
            throw new RuntimeException("File not found!");
        }
        List<String> fileList = new ArrayList<>();
    // filter for .txt files, case insensitive
        if (folder.exists() && folder.isDirectory()) {
            // Filter for .txt and .cip files (case-insensitive)
            File[] files = folder.listFiles((dir, name) -> {
                String lower = name.toLowerCase();
                return lower.endsWith(".txt") || lower.endsWith(".cip");
            });
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
