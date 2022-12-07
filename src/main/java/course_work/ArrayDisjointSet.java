package course_work;

/**
 * Реализация системы непересекающихся множеств на массиве.
 * Применены оптимизации: весовая эвристика и эвристика сжатия путей.
 * @see <a href="https://ru.algorithmica.org/cs/set-structures/dsu/">algoritmica.org</a>
 */
public class ArrayDisjointSet implements DisjointSet {
    private final int[] parents;
    private final int[] weights;

    /**
     * Создание множества из size элементов.
     *
     * @param size количество элементов
     */
    public ArrayDisjointSet(int size) {
        parents = new int[size];
        weights = new int[size];
        for (int i = 0; i < size; i++) {
            parents[i] = i;
            weights[i] = 1;
        }
    }

    /**
     * Данная реализация не предполагает явное создание множеств.
     *
     * @param value элемент
     */
    @Override
    public void makeSet(int value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Определение к какому множеству принадлежит элемент.
     * Применена эвристика пути, вершина переподвешивается за самую высокую.
     *
     * @param value элемент
     * @return идентификатор множества
     */
    @Override
    public int find(int value) {
        if (parents[value] == value) {
            return value;
        } else {
            return parents[value] = find(parents[value]);
        }
    }

    /**
     * Объединение двух множеств.
     *
     * @param a идентификатор первого множества
     * @param b идентификатор второго множества
     */
    @Override
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (weights[a] > weights[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        weights[b] += weights[a];
        parents[a] = b;
    }
}
