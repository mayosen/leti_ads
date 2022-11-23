package lab_3;

public class IllegalTreeEntry extends RuntimeException {
    public IllegalTreeEntry() {
        super("Некорректная запись дерева");
    }
}
