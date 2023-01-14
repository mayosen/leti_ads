package lab3;

public class IllegalTreeException extends RuntimeException {
    public IllegalTreeException(String message) {
        super("Некорректная запись дерева: " + message);
    }
}
