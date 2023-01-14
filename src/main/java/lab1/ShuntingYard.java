package lab1;

import java.util.EmptyStackException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ShuntingYard {
    static final String NUMBER_REGEX = "\\d+(/\\d+)?";
    static final String FUNCTION_REGEX = "sin|cos|round";
    static final String ARGUMENT_DELIMITER = ",";
    static final String DELIMITER = " ";
    static final String OPENING = "(";
    static final String CLOSING = ")";
    static final Map<String, Integer> operators = Map.of(
            "^", 100,
            "*", 50,
            "/", 50,
            "+", 10,
            "-", 10
    );

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

            } else if (Pattern.matches(ARGUMENT_DELIMITER, token)) {
                try {
                    while (true) {
                        if (!stack.peek().equals(OPENING)) {
                            builder.append(stack.pop());
                            builder.append(DELIMITER);
                        } else {
                            break;
                        }
                    }

                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException();
                }

            } else if (operators.containsKey(token)) {
                String anotherOperator;

                while (!stack.isEmpty() && operators.containsKey(anotherOperator = stack.peek())) {
                    if (operators.get(anotherOperator) >= operators.get(token)) {
                        stack.pop();
                        builder.append(anotherOperator);
                        builder.append(DELIMITER);
                    } else {
                        break;
                    }
                }

                stack.push(token);

            } else if (token.equals(OPENING)) {
                stack.push(token);

            } else if (token.equals(CLOSING)) {
                try {
                    while (!(stack.peek()).equals(OPENING)) {
                        builder.append(stack.pop());
                        builder.append(DELIMITER);
                    }

                    if (!stack.pop().equals(OPENING)) {
                        throw new IllegalArgumentException();
                    }

                    if (Pattern.matches(FUNCTION_REGEX, stack.peek())) {
                        builder.append(stack.pop());
                        builder.append(DELIMITER);
                    }

                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException();
                }

            } else {
                throw new IllegalArgumentException();
            }
        }

        while (!stack.isEmpty()) {
            String token = stack.pop();
            if (token.equals(OPENING)) {
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
