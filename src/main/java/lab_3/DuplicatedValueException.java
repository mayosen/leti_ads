package lab_3;

public class DuplicatedValueException extends RuntimeException {
    public DuplicatedValueException(int value) {
        super("Дерево уже содержит элемент " + value);
    }
}
