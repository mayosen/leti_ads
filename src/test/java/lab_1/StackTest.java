package lab_1;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    public void createEmptyStack() {
        Stack<Integer> stack = new SinglyLinkedList<>();
        assertTrue(stack.isEmpty());
        assertThrows(EmptyStackException.class, stack::peek);
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    public void createStack() {
        Stack<Integer> stack = new SinglyLinkedList<>();
        Integer value = 14;

        assertSame(value, stack.push(value));
        assertFalse(stack.isEmpty());
        assertSame(value, stack.peek());

        assertSame(value, stack.pop());
        assertTrue(stack.isEmpty());
        assertThrows(EmptyStackException.class, stack::peek);
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    public void createStackFromArray() {
        Integer first = 100;
        Integer second = 200;
        Integer third = 300;
        Stack<Integer> stack = new SinglyLinkedList<>(new Integer[]{first, second, third});

        assertSame(first, stack.pop());
        assertSame(second, stack.pop());
        assertSame(third, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void severalPushes() {
        Integer first = 100;
        Integer second = 200;
        Integer third = 300;
        Stack<Integer> stack = new SinglyLinkedList<>();

        assertSame(first, stack.push(first));
        assertSame(second, stack.push(second));
        assertSame(third, stack.push(third));

        assertSame(third, stack.peek());
        assertSame(third, stack.pop());
        assertSame(second, stack.pop());
        assertSame(first, stack.pop());
        assertTrue(stack.isEmpty());
    }
}
