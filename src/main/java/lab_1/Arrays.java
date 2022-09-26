package lab_1;

import java.lang.reflect.Array;

public class Arrays {
    public static <T> void arrayCopy(T[] src, int srcPos, T[] dest, int destPos, int length) {
        if (destPos <= srcPos) {
            for (int s = srcPos, d = destPos, i = 0; i < length; s++, d++, i++) {
                dest[d] = src[s];
            }
        } else {
            for (int s = srcPos + length - 1, d = destPos + length - 1, i = 0; i < length; s--, d--, i++) {
                dest[d] = src[s];
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] copyOf(T[] original, int newLength) {
        Class<?> newType = original.getClass();
        T[] copy = (newType == Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);

        arrayCopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }
}
