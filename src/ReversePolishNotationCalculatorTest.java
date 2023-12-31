import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationCalculatorTest {

    private final ReversePolishNotationCalculator rpn = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        assertEquals(7, rpn.calculatePolishNotation("3 4 +"));
    }

    @Test
    public void shouldCalculateMultiplication() {
        assertEquals(12, rpn.calculatePolishNotation("3 4 *"));
    }

    @Test
    public void shouldCalculateSubtraction() {
        assertEquals(-1, rpn.calculatePolishNotation("3 4 -"));
    }

    @Test
    public void shouldCalculateAdditionWithSpaces() {
        assertEquals(7, rpn.calculatePolishNotation("  3   4   +  "));
    }

    @Test
    public void shouldCalculateNegativeValues() {
        assertEquals(4, rpn.calculatePolishNotation("7 -3 +"));
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}