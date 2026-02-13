
import ciphers.CipherKeyLoader;
import ciphers.SubstitutionCipher;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


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
        String output = "";
        try {
            int index = Integer.parseInt(indexArg) - 1;
            List<String> files = fileHandler.getFileList();

            if (index < 0 || index >= files.size()) {
                return "Error: Invalid file selection.";
            }
            //load if its a cip file
            String filename = files.get(index);
            if(filename.endsWith(".cip")) {
                CipherKeyLoader objectA = new CipherKeyLoader();
                Path path = Paths.get("ciphers/key.txt");
                Map<Character, Character> cipherObject = objectA.load(path);
                SubstitutionCipher subCipher = new SubstitutionCipher(cipherObject);
                output = subCipher.decipher(fileHandler.readFile(filename));

            }else{
                output = fileHandler.readFile(filename);
            }

            return output;

        } catch (NumberFormatException e) {
            return "Error: File selection must be a number.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public String displayFileByIndex(String indexArg, String alternateKey) {

        String output = "";
            try {

                int index = Integer.parseInt(indexArg) - 1;
                List<String> files = fileHandler.getFileList();

                if (index < 0 || index >= files.size()) {
                    return "Error: Invalid file selection.";
                }
                //load if its a cip file
                String filename = files.get(index);
                if(filename.endsWith(".cip")) {
                    CipherKeyLoader objectA = new CipherKeyLoader();
                    Path path = Paths.get("ciphers/" + alternateKey);
                    Map<Character, Character> cipherObject = objectA.load(path);
                    SubstitutionCipher subCipher = new SubstitutionCipher(cipherObject);
                     output =subCipher.decipher(fileHandler.readFile(filename));

                }else{
                     output = fileHandler.readFile(filename);
                }

                return output;

            } catch (NumberFormatException e) {
                return "Error: File selection must be a number.";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
    //call load with default key or without, load(def key
}
