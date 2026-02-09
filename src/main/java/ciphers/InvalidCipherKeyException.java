package ciphers;

public class InvalidCipherKeyException extends RuntimeException {
    public InvalidCipherKeyException(String message) {
        super(message);
    }

    public InvalidCipherKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
