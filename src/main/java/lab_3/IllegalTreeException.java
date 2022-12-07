package lab_3;

public class IllegalTreeException extends RuntimeException {
    public IllegalTreeException(String message) {
        super("Некорректная запись дерева: " + message);
    }
}
