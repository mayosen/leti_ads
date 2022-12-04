package course_work;

import lab_1.List;
import lab_2.MergeSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Defence {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input2.txt"))) {
            List<Kruskal.Edge> treeEdges = Kruskal.getSpanningTree(reader);
            MergeSort.mergeSort(treeEdges, Comparator.comparing(Kruskal.Edge::getEdgeString));
            int totalWeight = 0;
            for (int i = 0; i < treeEdges.size(); i++) {
                Kruskal.Edge edge = treeEdges.get(i);
                System.out.println(edge.getEdgeString());
                totalWeight += edge.weight;
            }
            System.out.println(totalWeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
