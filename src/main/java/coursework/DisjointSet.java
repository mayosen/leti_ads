package coursework;

public interface DisjointSet {
    void makeSet(int value);

    int find(int value);

    void union(int a, int b);
}
