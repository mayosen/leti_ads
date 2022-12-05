package course_work;

import lab_1.ArrayList;
import lab_1.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KruskalTest {
    List<String> buildTree(String filename) {
        List<Kruskal.Edge> treeEdges = Defence.buildTreeFromFile("src/test/resources/course_work/" + filename);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < treeEdges.size(); i++) {
            result.add(treeEdges.get(i).getEdgeString());
        }
        return result;
    }

    @Test
    void tree() {
        List<String> actual = buildTree("tree.txt");
        List<String> expected = new ArrayList<>(new String[]{"A-B", "B-C", "C-E", "D-E", "C-F", "F-G"});
        assertEquals(expected, actual);
    }

    @Test
    void treeWiki() {
        List<String> actual = buildTree("tree_wiki.txt");
        List<String> expected = new ArrayList<>(new String[]{"A-D", "C-E", "D-F", "A-B", "B-E", "E-G"});
        assertEquals(expected, actual);
    }
}
