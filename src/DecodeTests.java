import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DecodeTests {
    @Test
    public void testIsEmpty() {
        Throwable throwableZeroLength = assertThrows(NumberFormatException.class, () -> Integer.decode(""));
        assertEquals("Zero length string", throwableZeroLength.getMessage());
    }

    @Test
    public void testSignedPositive() {
        assertEquals(1, Integer.decode("+1"));
    }

    @Test
    public void testSignedNegative() {
        assertEquals(-1, Integer.decode("-1"));
    }

    @Test
    public void testUnsignedPositive() {
        assertEquals(1, Integer.decode("1"));
    }

    @Test
    public void testHexDigitsX() {
        assertEquals(16, Integer.decode("0x10"));
        assertEquals(16, Integer.decode("0X10"));
    }

    @Test
    public void testHexDigitsSharp() {
        assertEquals(16, Integer.decode("#10"));
    }

    @Test
    public void testOctalDigits() {
        assertEquals(8, Integer.decode("010"));
    }

    @Test
    public void testWrongSignCharPosition() {
        Throwable throwableWithMinusPlus = assertThrows(NumberFormatException.class, () -> Integer.decode("-+1"));
        Throwable throwableWithDoubleMinus = assertThrows(NumberFormatException.class, () -> Integer.decode("--1"));
        assertEquals("Sign character in wrong position", throwableWithMinusPlus.getMessage());
        assertEquals("Sign character in wrong position", throwableWithDoubleMinus.getMessage());
    }

    @Test
    public void testGetValueOf() {
        Throwable thrown = assertThrows(NumberFormatException.class, () -> Integer.decode("сорок два"));
        assertEquals("For input string: \"сорок два\"", thrown.getMessage());
    }
}