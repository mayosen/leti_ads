package course_work;

import lab_1.ArrayList;
import lab_1.List;
import lab_2.MergeSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;

public class Kruskal {
    private final List<String> vertices;
    private final List<Edge> edges;

    private Kruskal(BufferedReader reader) throws IOException {
        vertices = getTokens(reader.readLine());
        edges = new ArrayList<>();

        for (int i = 0; i < vertices.size(); i++) {
            List<String> rowVertices = getTokens(reader.readLine());
            for (int j = i + 1; j < vertices.size(); j++) {
                int value = Integer.parseInt(rowVertices.get(j));
                if (value > 0) {
                    edges.add(new Edge(i, j, value));
                }
            }
        }
    }

    public static List<Edge> getSpanningTree(BufferedReader reader) throws IOException {
        return new Kruskal(reader).buildTree();
    }

    private List<Edge> buildTree() {
        MergeSort.mergeSort(edges, Comparator.comparingInt(e -> e.weight));
        DisjointSet set = new ArrayDisjointSet(vertices.size());
        List<Edge> tree = new ArrayList<>();

        for (int i = 0; i < edges.size() && tree.size() < vertices.size(); i++) {
            Edge edge = edges.get(i);
            int firstSet = set.find(edge.firstVertex);
            int secondSet = set.find(edge.secondVertex);
            if (firstSet != secondSet) {
                set.union(firstSet, secondSet);
                tree.add(edge);
            }
        }

        return tree;
    }

    private static List<String> getTokens(String line) {
        String[] parts = line.split("\\s+");
        List<String> tokens = new ArrayList<>();
        for (String value : parts) {
            if (!value.equals("")) {
                tokens.add(value);
            }
        }
        return tokens;
    }

    class Edge {
        final int firstVertex;
        final int secondVertex;
        final int weight;

        public Edge(int firstVertex, int secondVertex, int weight) {
            this.firstVertex = firstVertex;
            this.secondVertex = secondVertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "edge='" + getEdgeString() +
                    "', weight=" + weight +
                    '}';
        }

        public String getEdgeString() {
            return vertices.get(firstVertex) + vertices.get(secondVertex);
        }
    }
}
