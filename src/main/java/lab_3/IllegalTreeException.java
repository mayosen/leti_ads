package lab_3;

public class IllegalTreeException extends RuntimeException {
    public IllegalTreeException(String message) {
        super(String.format("Некорректная запись дерева: %s", message));
    }
}
