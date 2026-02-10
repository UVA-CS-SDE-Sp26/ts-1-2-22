package ciphers;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SubstitutionCipherTest {

    @Test
    void deciphersMappedCharacters() {
        // cipher -> actual mapping
        Map<Character, Character> map = Map.of(
                'X', 'A',
                'Y', 'B',
                'Z', 'C'
        );

        Cipher cipher = new SubstitutionCipher(map);

        assertEquals("ABC", cipher.decipher("XYZ"));
    }

    @Test
    void leavesUnmappedCharactersUnchanged() {
        Map<Character, Character> map = Map.of('X', 'A');
        Cipher cipher = new SubstitutionCipher(map);

        assertEquals("A! 1", cipher.decipher("X! 1"));
        assertEquals("HELLO", cipher.decipher("HELLO"));
    }

    @Test
    void handlesEmptyString() {
        Cipher cipher = new SubstitutionCipher(Map.of('X', 'A'));
        assertEquals("", cipher.decipher(""));
    }

    @Test
    void handlesNullInput() {
        Cipher cipher = new SubstitutionCipher(Map.of('X', 'A'));
        assertNull(cipher.decipher(null));
    }

    @Test
    void preservesLowercaseWhenUppercaseKeyUsed() {
        // If 'X' maps to 'A', lowercase 'x' should map to 'a'
        Cipher cipher = new SubstitutionCipher(Map.of('X', 'A'));

        assertEquals("a", cipher.decipher("x"));
        assertEquals("a!", cipher.decipher("x!"));
    }
}
