package lab_1;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static lab_1.ShuntingYard.run;
import static org.junit.jupiter.api.Assertions.*;

public class ShuntingYardTest {
    @Test
    public void numberRegex() {
        assertTrue(Pattern.matches(ShuntingYard.NUMBER_REGEX, "4"));
        assertTrue(Pattern.matches(ShuntingYard.NUMBER_REGEX, "123"));
        assertTrue(Pattern.matches(ShuntingYard.NUMBER_REGEX, "123/123"));
        assertTrue(Pattern.matches(ShuntingYard.NUMBER_REGEX, "0/0"));
        assertFalse(Pattern.matches(ShuntingYard.NUMBER_REGEX, "0/"));
        assertFalse(Pattern.matches(ShuntingYard.NUMBER_REGEX, ""));
        assertFalse(Pattern.matches(ShuntingYard.NUMBER_REGEX, "test"));
        assertFalse(Pattern.matches(ShuntingYard.NUMBER_REGEX, "("));
    }

    @Test
    public void functionRegex() {
        assertTrue(Pattern.matches(ShuntingYard.FUNCTION_REGEX, "sin"));
        assertTrue(Pattern.matches(ShuntingYard.FUNCTION_REGEX, "cos"));
        assertFalse(Pattern.matches(ShuntingYard.FUNCTION_REGEX, "123"));
        assertFalse(Pattern.matches(ShuntingYard.FUNCTION_REGEX, "1.1"));
        assertFalse(Pattern.matches(ShuntingYard.FUNCTION_REGEX, ")"));
    }

    @Test
    public void emptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, run(input));
    }

    @Test
    public void singleDigit() {
        String input = "4";
        String expected = "4";
        assertEquals(expected, run(input));
    }

    @Test
    public void unusedOperator() {
        String input = "4 +";
        String expected = "4 +";
        assertEquals(expected, run(input));
    }

    @Test
    public void simpleAddiction() {
        String input = "2 + 2";
        String expected = "2 2 +";
        assertEquals(expected, run(input));
    }

    @Test
    public void complexSample1() {
        String input = "2 * 2 + 3/4";
        String expected = "2 2 * 3/4 +";
        assertEquals(expected, run(input));
    }

    @Test
    public void complexSample2() {
        String input = "sin ( 2 + 3 ^ 4 ^ 5 )";
        String expected = "2 3 4 ^ 5 ^ + sin";
        assertEquals(expected, run(input));
    }

    @Test
    public void illegalSymbol() {
        String input = "2 + sin ( 5 + 3 ) * 8 + ( 9 * 7 ) !";
        assertThrows(IllegalArgumentException.class, () -> run(input));
    }

    @Test
    public void complexSample3() {
        String input = "2 + sin ( 5 + 3 ) * 8 + ( 9 * 7 )";
        String expected = "2 5 3 + sin 8 * + 9 7 * +";
        assertEquals(expected, run(input));
    }

    @Test
    public void complexSample4() {
        String input = "3 + 4 * 2 / ( 1 - 5 ) ^ 2";
        String expected = "3 4 2 * 1 5 - 2 ^ / +";
        assertEquals(expected, run(input));
    }
}
