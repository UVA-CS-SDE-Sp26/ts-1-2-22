package ciphers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class CipherKeyLoader {

    public CipherKeyLoader() {}

    /**
     * Loads a substitution cipher key file.
     * Line 1 = actual/plaintext characters
     * Line 2 = cipher characters
     * Returns map of cipherChar -> actualChar
     */
    public static Map<Character, Character> load(Path keyPath) {
        List<String> lines;
        try {
            lines = Files.readAllLines(keyPath);
        } catch (IOException e) {
            throw new InvalidCipherKeyException("Cannot read cipher key file: " + keyPath, e);
        }

        if (lines.size() < 2) {
            throw new InvalidCipherKeyException("Cipher key file must contain at least 2 lines.");
        }

        String actual = lines.get(0);
        String cipher = lines.get(1);

        // Your tests expect empty line to be invalid
        if (actual == null || cipher == null || actual.isEmpty() || cipher.isEmpty()) {
            throw new InvalidCipherKeyException("Cipher key lines cannot be empty.");
        }

        if (actual.length() != cipher.length()) {
            throw new InvalidCipherKeyException("Cipher key lines must have the same length.");
        }

        if (hasDuplicates(actual)) {
            throw new InvalidCipherKeyException("Plaintext line contains duplicate characters.");
        }

        if (hasDuplicates(cipher)) {
            throw new InvalidCipherKeyException("Ciphertext line contains duplicate characters.");
        }

        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < cipher.length(); i++) {
            map.put(cipher.charAt(i), actual.charAt(i));
        }

        return Collections.unmodifiableMap(map);
    }

    private static boolean hasDuplicates(String s) {
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.add(c)) return true;
        }
        return false;
    }
}
