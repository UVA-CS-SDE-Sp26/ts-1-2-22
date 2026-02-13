package ciphers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCipherKeyExceptionTest {

    @Test
    void messageConstructorSetsMessage() {
        InvalidCipherKeyException ex = new InvalidCipherKeyException("bad key");
        assertEquals("bad key", ex.getMessage());
    }

    @Test
    void messageAndCauseConstructorSetsCause() {
        RuntimeException cause = new RuntimeException("io");
        InvalidCipherKeyException ex = new InvalidCipherKeyException("bad key", cause);
        assertEquals("bad key", ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}
