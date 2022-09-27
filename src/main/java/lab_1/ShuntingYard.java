package lab_1;

import java.util.EmptyStackException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ShuntingYard {
    public static final String NUMBER_REGEX = "\\d+(/\\d+)?";
    public static final String FUNCTION_REGEX = "sin|cos";
    private static final String DELIMITER = " ";
    private static final Map<String, Integer> operators = Map.of(
            "^", 100,
            "*", 50,
            "/", 50,
            "+", 10,
            "-", 10
    );

    public static String run(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, DELIMITER);
        StringBuilder builder = new StringBuilder();
        Stack<String> operatorStack = new SinglyLinkedList<>();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (Pattern.matches(NUMBER_REGEX, token)) {
                builder.append(token);
                builder.append(DELIMITER);

            } else if (Pattern.matches(FUNCTION_REGEX, token)) {
                operatorStack.push(token);

            } else if (operators.containsKey(token)) {
                String op;
                while (!operatorStack.isEmpty() && operators.containsKey(op = operatorStack.peek())) {
                    if (operators.get(op) >= operators.get(token)) {
                        operatorStack.pop();
                        builder.append(op);
                        builder.append(DELIMITER);
                    } else {
                        break;
                    }
                }
                operatorStack.push(token);

            } else if (token.equals("(")) {
                operatorStack.push(token);

            } else if (token.equals(")")) {
                try {
                    while (!(operatorStack.peek()).equals("(")) {
                        builder.append(operatorStack.pop());
                        builder.append(DELIMITER);
                    }
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException();
                }

                if (operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                }

                if (Pattern.matches(FUNCTION_REGEX, operatorStack.peek())) {
                    builder.append(operatorStack.pop());
                    builder.append(DELIMITER);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        while (!operatorStack.isEmpty()) {
            String token = operatorStack.pop();
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
