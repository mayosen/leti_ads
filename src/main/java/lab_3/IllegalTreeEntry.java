package lab_3;

public class IllegalTreeEntry extends RuntimeException {
    public IllegalTreeEntry(String message) {
        super(String.format("Некорректная запись дерева: %s", message));
    }
}
