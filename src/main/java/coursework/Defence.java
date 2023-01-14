package coursework;

import lab1.List;
import lab2.MergeSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Defence {
    public static void main(String[] args) {
        List<Kruskal.Edge> treeEdges = buildTreeFromFile("input.txt");
        MergeSort.mergeSort(treeEdges, Comparator.comparing(Kruskal.Edge::getEdgeString));
        int totalWeight = 0;
        for (int i = 0; i < treeEdges.size(); i++) {
            Kruskal.Edge edge = treeEdges.get(i);
            System.out.println(edge.getEdgeString());
            totalWeight += edge.weight;
        }
        System.out.println(totalWeight);
    }

    public static List<Kruskal.Edge> buildTreeFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return Kruskal.buildTree(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
