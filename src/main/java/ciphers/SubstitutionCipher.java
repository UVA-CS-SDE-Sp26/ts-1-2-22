package ciphers;

import java.util.Map;

public final class SubstitutionCipher implements Cipher {

    private final Map<Character, Character> cipherToActual;

    public SubstitutionCipher(Map<Character, Character> cipherToActual) {
        if (cipherToActual == null) {
            throw new IllegalArgumentException("cipherToActual map cannot be null");
        }
        this.cipherToActual = cipherToActual;
    }

    @Override
    public String decipher(String input) {
        if (input == null) return null;

        StringBuilder out = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            Character mapped = cipherToActual.get(ch);
            if (mapped != null) {
                out.append(mapped);
                continue;
            }

            // lowercase support: map uppercase, preserve lowercase output
            char upper = Character.toUpperCase(ch);
            Character mappedUpper = cipherToActual.get(upper);
            if (mappedUpper != null && Character.isLowerCase(ch)) {
                out.append(Character.toLowerCase(mappedUpper));
            } else {
                out.append(ch);
            }
        }
        return out.toString();
    }
}
