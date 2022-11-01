package lab_1;

public interface Stack<E> {
    E push(E item);

    E pop();

    E peek();

    boolean isEmpty();

    int size();
}
