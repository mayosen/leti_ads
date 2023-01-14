package lab1;

public class Arrays {
    public static void arrayCopy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
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

    public static Object[] copyOf(Object[] original, int newLength) {
        Object[] copy = new Object[newLength];
        arrayCopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }
}
