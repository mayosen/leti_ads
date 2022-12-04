package course_work;

public class ArrayDisjointSet implements DisjointSet {
    private final int[] parents;

    public ArrayDisjointSet(int size) {
        parents = new int[size];
        for (int i = 0; i < size; i++) {
            makeSet(i);
        }
    }

    @Override
    public void makeSet(int value) {
        parents[value] = value;
    }

    @Override
    public int find(int value) {
        return parents[value];
    }

    @Override
    public void union(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);
        int maxParent = Math.max(firstParent, secondParent);
        int minParent = Math.min(firstParent, secondParent);
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == minParent) {
                parents[i] = maxParent;
            }
        }
    }

    // TODO: Перейти на https://ru.algorithmica.org/cs/set-structures/dsu/
}
