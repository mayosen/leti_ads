package lab_1;

import java.util.EmptyStackException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ShuntingYard {
    public static final String NUMBER_REGEX = "\\d+(/\\d+)?";
    public static final String FUNCTION_REGEX = "sin|cos";
    private static final String DELIMITER = " ";
    private static final List<String> operators = new ArrayList<>(new String[]{"^", "*", "/", "+", "-"});
    /*
    private static final Map<String, Integer> operators = Map.of(
            "^", 100,
            "*", 50,
            "/", 50,
            "+", 10,
            "-", 10
    );
    */

    private static int getPriority(String token) {
        return switch (token) {
            case "^" -> 100;
            case "*", "/" -> 50;
            case "+", "-" -> 10;
            default -> throw new IllegalArgumentException();
        };
    }

    public static String run(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, DELIMITER);
        StringBuilder builder = new StringBuilder();
        Stack<String> stack = new SinglyLinkedList<>();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (Pattern.matches(NUMBER_REGEX, token)) {
                builder.append(token);
                builder.append(DELIMITER);

            } else if (Pattern.matches(FUNCTION_REGEX, token)) {
                stack.push(token);

            } else if (operators.contains(token)) {
                String op;
                while (!stack.isEmpty() && operators.contains(op = stack.peek())) {
                    int tokenPriority = getPriority(token);
                    int operatorPriority = getPriority(op);

                    if (operatorPriority >= tokenPriority) {
                        stack.pop();
                        builder.append(op);
                        builder.append(DELIMITER);
                    } else {
                        break;
                    }
                }
                stack.push(token);

            } else if (token.equals("(")) {
                stack.push(token);

            } else if (token.equals(")")) {
                try {
                    while (!(stack.peek()).equals("(")) {
                        builder.append(stack.pop());
                        builder.append(DELIMITER);
                    }
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException();
                }

                if (stack.peek().equals("(")) {
                    stack.pop();
                }

                if (Pattern.matches(FUNCTION_REGEX, stack.peek())) {
                    builder.append(stack.pop());
                    builder.append(DELIMITER);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        while (!stack.isEmpty()) {
            String token = stack.pop();
            if (token.equals("(")) {
                throw new IllegalArgumentException();
            }
            builder.append(token);
            builder.append(DELIMITER);
        }

        if (!builder.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
