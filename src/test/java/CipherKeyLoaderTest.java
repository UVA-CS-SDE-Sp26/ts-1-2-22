package ciphers;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CipherKeyLoaderTest {

    @Test
    void loadsValidKey_createsCorrectMapping() throws Exception {
        Path key = Files.createTempFile("key", ".txt");
        // Line 1 = actual, Line 2 = cipher
        Files.writeString(key, "ABC\nXYZ\n");

        Map<Character, Character> map = CipherKeyLoader.load(key);

        assertEquals('A', map.get('X'));
        assertEquals('B', map.get('Y'));
        assertEquals('C', map.get('Z'));
    }

    @Test
    void missingFile_throws() {
        Path key = Path.of("this_file_should_not_exist_123456789.txt");
        assertThrows(InvalidCipherKeyException.class, () -> CipherKeyLoader.load(key));
    }

    @Test
    void fewerThanTwoLines_throws() throws Exception {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "ABC\n"); // only one line

        assertThrows(InvalidCipherKeyException.class, () -> CipherKeyLoader.load(key));
    }

    @Test
    void differentLengths_throws() throws Exception {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "ABCD\nXYZ\n");

        assertThrows(InvalidCipherKeyException.class, () -> CipherKeyLoader.load(key));
    }

    @Test
    void duplicatesInPlaintext_throws() throws Exception {
        Path key = Files.createTempFile("key", ".txt");
        // duplicates in line 1: A repeats
        Files.writeString(key, "AABC\nWXYZ\n");

        assertThrows(InvalidCipherKeyException.class, () -> CipherKeyLoader.load(key));
    }

    @Test
    void duplicatesInCiphertext_throws() throws Exception {
        Path key = Files.createTempFile("key", ".txt");
        // duplicates in line 2: X repeats
        Files.writeString(key, "ABCD\nXXYZ\n");

        assertThrows(InvalidCipherKeyException.class, () -> CipherKeyLoader.load(key));
    }

    @Test
    void emptyLine_throws() throws Exception {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "\nABC\n");

        assertThrows(InvalidCipherKeyException.class, () -> CipherKeyLoader.load(key));
    }
}
